package com.company.project.config;

import com.company.project.cache.AerospikeCacheManager;
import com.company.project.cache.CacheConfig;
import com.company.project.cache.CacheConstants;
import com.company.project.dto.AerospikeProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ishan.bansal on 8/13/15.
 */

@Configuration
@PropertySource(value = {"classpath:aerospike.properties"})
public class DataConfig {
    @Autowired
    Environment env;

    @Bean
    public AerospikeProperties aerospikeProperties() {
        String hostUrl = env.getProperty("aerospike.db.host", String.class);
        String name = env.getProperty("aerospike.db.name", String.class);
        int hostPort = env.getProperty("aerospike.db.port", Integer.class);

        AerospikeProperties aerospikeProperties = new AerospikeProperties();
        aerospikeProperties.setHost(hostUrl);
        aerospikeProperties.setPort(hostPort);
        aerospikeProperties.setName(name);
        return aerospikeProperties;
    }

    @Bean(name = CacheConstants.AEROSPIKE_CACHE_MANAGER)
    public CacheManager aerospikeCacheManager(){
        AerospikeProperties aerospikeProperties = aerospikeProperties();
        AerospikeCacheManager aerospikeCacheManager = new AerospikeCacheManager(aerospikeProperties.getHost(),
                                                            aerospikeProperties.getPort(),aerospikeProperties.getName());
        aerospikeCacheManager.setExpires(getExpireMap());
        return aerospikeCacheManager;
    }

    private Map<String, Long> getExpireMap() {
        Map<String, Long> expireMap = new HashMap<String, Long>();
        Field[] fields = CacheConstants.class.getFields();
        for (Field field : fields) {
            CacheConfig cacheConfig = field.getAnnotation(CacheConfig.class);
            if (cacheConfig != null) {
                try {
                    if (cacheConfig.ttl() != -1) {
                        expireMap.put((String) field.get(null), cacheConfig.ttl());
                    }
                } catch (IllegalAccessException iae) {
                    // ignore
                }
            }
        }
        return expireMap;
    }


//    public CacheManager cacheManager() {
//        GuavaCacheManager guavaCacheManager =  new GuavaCacheManager();
//        guavaCacheManager.setCacheBuilder(CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES));
//        return guavaCacheManager;
//    }
}
