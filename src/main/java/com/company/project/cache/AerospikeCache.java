package com.company.project.cache;

import com.aerospike.client.*;
import com.aerospike.client.async.AsyncClient;
import com.aerospike.client.listener.WriteListener;
import com.aerospike.client.policy.ScanPolicy;
import com.aerospike.client.policy.WritePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.support.SimpleValueWrapper;

import java.util.concurrent.atomic.AtomicLong;

public class AerospikeCache implements Cache {

    private static final ValueWrapper NULL_VALUE_WRAPPER = new SimpleValueWrapper(null);


    private static final Logger logger = LoggerFactory.getLogger(AerospikeCache.class);
    private String name;
    private AerospikeClient client;
    private AsyncClient asyncClient;
    private WritePolicy policy;
    private String namespace;
    private boolean useAsyncPut;
    private CacheStatsBuilder cacheStatsBuilder;

    public AerospikeCache() {
    }

    public AerospikeCache(String namespace, String name,
                          AerospikeClient client, AsyncClient asyncClient,
                          WritePolicy policy) {
        this.namespace = namespace;
        this.name = name;
        this.client = client;
        this.asyncClient = asyncClient;
        this.policy = policy;
        this.useAsyncPut = true;
        cacheStatsBuilder = new CacheStatsBuilder();
    }

    public AerospikeCache(String namespace, String name,
                          AerospikeClient client, AsyncClient asyncClient,
                          WritePolicy policy, boolean useAsyncPut) {
        this(namespace, name, client, asyncClient, policy);
        this.useAsyncPut = useAsyncPut;
    }

    @Override
    public Object getNativeCache() {
        return null;
    }

    @Override
    public <T> T get(Object key, Class<T> type) {
        T result = null;
        try {
            ValueWrapper existingValue = get(key);
            Object value = null;

            if(existingValue != null)
                value = existingValue.get();
            if (value != null && type != null && !type.isInstance(value)) {
                throw new IllegalStateException("Cached value is not of required type [" + type.getName() + "]: " + value);
            }
            return (T) value;

        } catch (Exception eX) {
            String errorString = String.format("Failed to get value for key: %s and type: %s.",
                    key, type);
            logger.error(errorString, eX);
        }
        return result;
    }

    public ValueWrapper get(Object key) {
        Record record = null;
        try {
            if (policy == null) {
                return null;
            }
            long startTime = System.nanoTime();
            record = client.get(policy, new Key(namespace, name, Value.get(key)));
            cacheStatsBuilder.registerGetTime(System.nanoTime() - startTime);
            if (record != null)
                cacheStatsBuilder.incrementGets();
            
        } catch (Exception e) {
            logger.error("Error while getting key " + key + " value for cache name " + name, e);
            cacheStatsBuilder.incrementExceptions();
        }
        if (record != null)
            return new SimpleValueWrapper(record.getValue("v"));

        cacheStatsBuilder.incrementMisses();
        return null;
    }

    @Override
    public ValueWrapper putIfAbsent(Object key, Object value) {
        ValueWrapper existingValue = get(key);
        if (existingValue == null) {
            put(key, value);
            return null;
        } else {
            return existingValue;
        }
    }

    public void put(final Object key, final Object value) {
        try {
            if (policy == null) {
                return;
            }
            Key aerospikeKey = new Key(namespace, name, Value.get(key));
            Bin[] bins = new Bin[] {new Bin("v", value), new Bin("t", System.currentTimeMillis())};
            long startTime = System.nanoTime();
            if (useAsyncPut) {
                asyncClient.put(policy, new WriteListener() {
                    @Override
                    public void onSuccess(Key key) {
                        logger.debug("Added key : " + key);
                    }

                    @Override
                    public void onFailure(AerospikeException e) {
                        logger.error("Error adding key " + key + " value for cache name " + name, e);
                    }
                }, aerospikeKey, bins);
            } else {
                client.put(policy, aerospikeKey, bins);
            }
            cacheStatsBuilder.registerPutTime(System.nanoTime() - startTime);
            cacheStatsBuilder.incrementPuts();

        } catch (Exception e) {
            logger.error("Error while adding key " + key + " value for cache name " + name, e);
            cacheStatsBuilder.incrementExceptions();
        }
    }
   
    public void evict(Object key) {
        try {
            client.delete(policy, new Key(namespace, name, Value.get(key)));
        } catch (Exception e) {
            logger.error("Error while deleting key " + key + " value for cache name " + name, e);
        }
    }

    public void clear() {
        try {
            final AerospikeClient aerospikeClient = this.client;
            final WritePolicy writePolicy = this.policy;

            ScanPolicy scanPolicy = new ScanPolicy();
            final AtomicLong count = new AtomicLong();
            aerospikeClient.scanAll(scanPolicy, namespace, name, new ScanCallback() {

                public void scanCallback(Key key, Record record) throws AerospikeException {
                    aerospikeClient.delete(writePolicy, key);
                    count.incrementAndGet();
                }
            }, new String[]{});
            logger.info("Deleted " + count.get() + " records from set " + name);
        } catch (AerospikeException e) {
            logger.error("Error while clearing cache " + e);
        }
    }

    public WritePolicy getPolicy() {
        return policy;
    }

    public void setPolicy(WritePolicy policy) {
        this.policy = policy;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void scan(final CacheScanListener cacheScanListener) {
        try {
            final AerospikeClient aerospikeClient = this.client;

            ScanPolicy scanPolicy = new ScanPolicy();
            aerospikeClient.scanAll(scanPolicy, namespace, name, new ScanCallback() {

                public void scanCallback(Key key, Record record) throws AerospikeException {
                    cacheScanListener.onRecordScan(key, record.getValue("v"), record.getValue("t"));
                }
            }, new String[]{});
        } catch (AerospikeException e) {
            logger.error("Error while clearing cache " + e);
        }
    }

    public String getStats() {
        return cacheStatsBuilder.getCacheStats();
    }
}
