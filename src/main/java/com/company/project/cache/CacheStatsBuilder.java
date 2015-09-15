package com.company.project.cache;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by prashant.saksena on 31-03-2015.
 */
public class CacheStatsBuilder {
    private AtomicInteger gets;
    private AtomicInteger puts;
    private AtomicInteger exceptions;
    private AtomicInteger misses;
    private AtomicLong    cacheGetTime;
    private AtomicLong    cachePutTime;

    public CacheStatsBuilder() {
        this.gets = new AtomicInteger(0);
        this.puts = new AtomicInteger(0);
        this.exceptions = new AtomicInteger(0);
        this.misses = new AtomicInteger(0);
        this.cacheGetTime = new AtomicLong(0);
        this.cachePutTime = new AtomicLong(0);
    }

    public void incrementGets() {
        gets.incrementAndGet();
    }

    public void incrementPuts() {
        puts.incrementAndGet();
    }

    public void incrementExceptions() {
        exceptions.incrementAndGet();
    }

    public void incrementMisses() {
        misses.incrementAndGet();
    }

    public void registerGetTime(long time) {
        cacheGetTime.addAndGet(time);
    }

    public void registerPutTime(long time) {
        cachePutTime.addAndGet(time);
    }

    public String getCacheStats() {
        int getCount = gets.getAndSet(0);
        int putCount = puts.getAndSet(0);
        int exceptionCount = exceptions.getAndSet(0);
        int missCount = misses.getAndSet(0);

        float hitRatio = -1f;
        if (getCount + putCount != 0)
             hitRatio = (float) (getCount / (getCount + putCount));

        long avgGetTime = 0;
        if (getCount + missCount != 0)
            avgGetTime = cacheGetTime.get()/(getCount + missCount);
        cacheGetTime.set(0);

        long avgPutTime = 0;
        if (putCount != 0)
            avgPutTime = cachePutTime.get()/putCount;
        cachePutTime.set(0);

        StringBuilder sb = new StringBuilder();
        sb.append("[").append(getCount).append(",")
                .append(putCount).append(",")
                .append(exceptionCount).append(",")
                .append(missCount).append(",")
                .append(hitRatio).append(",")
                .append(avgGetTime).append(",")
                .append(avgPutTime).append("]");

        return sb.toString();
    }
}
