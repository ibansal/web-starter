package com.company.project.cache;

import java.lang.annotation.*;

/**
 * Use to provide metadata for cache config. Target {@link java.lang.annotation.ElementType.FIELD} can be
 * used to provide metadata info for a cache zone while {@link java.lang.annotation.ElementType.METHOD} can
 * be used for individual key cache info (if provider supports)
 */

@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface CacheConfig {
    /*
       time to live (usually for a cache zone). -1 indicates no expiry
     */
    long ttl() default -1;
}
