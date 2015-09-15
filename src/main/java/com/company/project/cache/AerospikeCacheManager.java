package com.company.project.cache;

import com.aerospike.client.AerospikeClient;
import com.aerospike.client.Host;
import com.aerospike.client.async.AsyncClient;
import com.aerospike.client.async.AsyncClientPolicy;
import com.aerospike.client.policy.ClientPolicy;
import com.aerospike.client.policy.WritePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class AerospikeCacheManager implements CacheManager {

    private static Logger logger = LoggerFactory.getLogger(AerospikeCacheManager.class);

    private AerospikeClient syncAerospikeClient;
    private AsyncClient asyncAerospikeClient;

    private String cacheNamespace = null;

    private Map<String,Cache> cacheMap = new ConcurrentHashMap<String,Cache>();

    private static int DEFAULT_EXPIRATION = 5*60;
    private Map<String, Long> expires = null;

    public AerospikeCacheManager(String hosts,int port,String cacheNamespace) {
        this.cacheNamespace = cacheNamespace;
        String[] hostIPs = hosts.split(",");
        Host[] ahosts = new Host[hostIPs.length];
        for (int i = 0; i < hostIPs.length; i++) {
            ahosts[i] = new Host(hostIPs[i], port);
        }
        AsyncClientPolicy asyncPolicy = new AsyncClientPolicy();
        asyncPolicy.asyncSelectorThreads = 4;
        asyncPolicy.asyncTaskThreadPool = new ThreadPoolExecutor(0, 20, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
                new NamingThreadFactory(), new CustomCallerRunsPolicy());

        asyncAerospikeClient = new AsyncClient(asyncPolicy, ahosts);
        ClientPolicy policy = new ClientPolicy();
        syncAerospikeClient = new AerospikeClient(policy, ahosts);

    }

    public Cache registerCache(String cacheName,int expiration)
    {
        return registerCache(cacheName, expiration, false);
    }

    public Cache registerCache(String cacheName,int expiration, boolean useAsyncPut)
    {
        Cache cache = cacheMap.get(cacheName.toLowerCase());
        if(cache != null)
            return cache;

        WritePolicy writePolicy = new WritePolicy();
        writePolicy.expiration = expiration;
        writePolicy.timeout = 5000;
        writePolicy.sleepBetweenRetries = 10;

        AerospikeCache aerospikeCache = new AerospikeCache(cacheNamespace,cacheName,syncAerospikeClient,asyncAerospikeClient,
                writePolicy, useAsyncPut);
        cacheMap.put(cacheName.toLowerCase(),aerospikeCache);
        return aerospikeCache;
    }

    @Override
    public Cache getCache(String cacheName) {
        return registerCache(cacheName, computeExpiration(cacheName));
    }

    @Override
    public Collection<String> getCacheNames() {
        return cacheMap.keySet();
    }

    private class CustomCallerRunsPolicy extends ThreadPoolExecutor.CallerRunsPolicy {
        private AtomicLong count = new AtomicLong(0l);

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            logger.info("Rejection policy invoked ... total rejected count {}", count.incrementAndGet());
            super.rejectedExecution(r, e);
        }
    }

    private class NamingThreadFactory implements ThreadFactory {
        private final AtomicLong count = new AtomicLong(0l);

        public Thread newThread(Runnable r) {
            return new Thread(r, "ACS_Snapshop_Write_Threads" + "_" + count.incrementAndGet());
        }
    }

    public void scanCache(String cacheName, CacheScanListener cacheScanListener) {
        Cache cache = cacheMap.get(cacheName.toLowerCase());
        if (cache == null) {
            return;
        }
        AerospikeCache aerospikeCache = (AerospikeCache) cache;
        aerospikeCache.scan(cacheScanListener);
    }

    private int computeExpiration(String name) {
        Long expiration = null;
        if (expires != null) {
            expiration = expires.get(name);
        }
        return (expiration != null ? expiration.intValue() : DEFAULT_EXPIRATION);
    }

    /**
     * Sets the expire time (in seconds) for cache regions (by key).
     *
     * @param expires time in seconds
     */
    public void setExpires(Map<String, Long> expires) {
        this.expires = (expires != null ? new ConcurrentHashMap<String, Long>(expires) : null);
    }


}
