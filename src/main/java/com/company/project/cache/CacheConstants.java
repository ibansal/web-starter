package com.company.project.cache;


import com.google.common.base.Preconditions;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by ishan.bansal on 8/18/15.
 */
public class CacheConstants {

    @CacheConfig(ttl = -1)
    public static final String CACHE_NEVER_EXPIRE = "never-expire";

    @CacheConfig(ttl = 5)
    public static final String CACHE_FIVE_SEC = "5-sec";

    @CacheConfig(ttl = 20)
    public static final String CACHE_TWENTY_SEC = "20-sec";

    @CacheConfig(ttl = 1 * 60)
    public static final String CACHE_ONE_MIN = "1-min";

    @CacheConfig(ttl = 2 * 60)
    public static final String CACHE_TWO_MIN = "2-min";

    @CacheConfig(ttl = 5 * 60)
    public static final String CACHE_FIVE_MIN = "5-min";

    @CacheConfig(ttl = 10 * 60)
    public static final String CACHE_TEN_MIN = "10-min";

    @CacheConfig(ttl = 15 * 60)
    public static final String CACHE_FIFTEEN_MIN = "15-min";

    @CacheConfig(ttl = 30 * 60)
    public static final String CACHE_THIRTY_MIN = "30-min";

    @CacheConfig(ttl = 60 * 60)
    public static final String CACHE_ONE_HOUR = "1-hour";

    @CacheConfig(ttl = 3 * 60 * 60)
    public static final String CACHE_THREE_HOUR = "3-hour";

    @CacheConfig(ttl = 6 * 60 * 60)
    public static final String CACHE_SIX_HOUR = "6-hour";

    @CacheConfig(ttl = 24 * 60 * 60)
    public static final String CACHE_ONE_DAY = "1-day";

    @CacheConfig(ttl = 10 * 24 * 60 * 60)
    public static final String CACHE_10_DAY = "10-day";

    public static final String PREFIX_PART = "(#root.methodName";

    public static final String CACHE_KEY_GEN = "T(com.company.project.cache.CacheConstants).buildCacheKey";

    public static final String CACHE_KEY_GEN_PREFIX = CACHE_KEY_GEN + PREFIX_PART;

    public static final String AEROSPIKE_CACHE_MANAGER = "aerospikeCacheManager";

    public static final String CACHE_KEY_ARGS_SEPARATOR = ":";

    public static final String CACHE_KEY_PREFIX_SEPARATOR = "-";


    public static String buildCacheKey(String cachePrefix, Object... cacheSuffixes) {
        String cacheKey = internalBuildCacheKey(cachePrefix, cacheSuffixes);
//        System.out.println(cacheKey);
        return cacheKey;
    }

    /**
     * Helper method to build the cache key.
     *
     * @param cachePrefix   The prefix to add to the cache key
     * @param cacheSuffixes The suffixed appended to the cache key   @return The cache key.
     */
    private static String internalBuildCacheKey(String cachePrefix, Object... cacheSuffixes) {
        Preconditions.checkArgument(StringUtils.isNotBlank(cachePrefix), "Cache Prefix cannot be empty, please supply a valid cache prefix");
        StringBuilder cacheKeyBuilder = new StringBuilder("snaplite").append(CACHE_KEY_PREFIX_SEPARATOR).append(cachePrefix);

        if (!ArrayUtils.isEmpty(cacheSuffixes)) {
            for (Object cacheSuffix : cacheSuffixes) {
                if (cacheSuffix != null) {
                    cacheKeyBuilder.append(CACHE_KEY_ARGS_SEPARATOR).append(cacheSuffix.toString());
                }
            }
        }
        return cacheKeyBuilder.toString();
    }

}
