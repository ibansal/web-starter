package com.company.project.cache;

/**
 * Created by prashant.saksena on 07-04-2015.
 */
public interface CacheScanListener {
    public void onRecordScan(Object key, Object value, Object updateTime);
}
