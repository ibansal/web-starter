package com.company.project.service;

import com.company.project.cache.CacheConstants;
import com.company.project.dto.MobileApiDto;
import com.company.project.utils.HTTPUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class HomePageService {

	@Autowired
	MobileApiDto mobileApiDto;


	@Cacheable(value = CacheConstants.CACHE_ONE_DAY, key = CacheConstants.CACHE_KEY_GEN_PREFIX
			+ ",#lang)", cacheManager = CacheConstants.AEROSPIKE_CACHE_MANAGER)
	public String getGoogleData(String lang) {


		String apidata = HTTPUtils.get("www.google.com");

		if (StringUtils.isBlank(apidata)) {
			return "";
		}
		return apidata;
	}
}
