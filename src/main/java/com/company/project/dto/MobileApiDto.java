package com.company.project.dto;

import java.io.Serializable;

/**
 * Created by ishan.bansal on 8/20/15.
 */


public class MobileApiDto implements Serializable {
    private String baseUrl;
    private String trendingUrl;
    private String bannersUrl;
    private String categoryUrl;
    private String assetVersion;
    private int numberOfRecords;
    private String localBaseUrl;
    private String localPDPUrl;
    private String thumborBaseUrl;
    //below one needs to be removed
    private String thumborBannerUrl;
    private String thumborWebpUrl;
    private String thumborIosUrl;

    private String dynamicFilterUrl;
    private String localizeUrl;
    private String categoryProductsUrl;
    private String relatedSearchUrl;
	

    private String filterUrl;
	private String offersUrl;
	
	private int categoryTreeDepth;
	private int noOfBanners;
	private int bannerStart;
	private String refreshProductDetailsUrl;
	

    private String verifyPinCodeUrl;
	private String similarProductsUrl;
	
	private int noOfOffers;
	private int offerStart;
	
	private String serchAutoSuggestUrl;

	private String searchGetUrl;

	private String sortCategoryUrl;

	
	
    public String getRelatedSearchUrl() {
        return relatedSearchUrl;
    }

    public void setRelateSearchUrl(String relatedSearchUrl) {
        this.relatedSearchUrl = relatedSearchUrl;
    }
    public String getSearchGetUrl() {
		return searchGetUrl;
	}

	public void setSearchGetUrl(String searchGetUrl) {
		this.searchGetUrl = searchGetUrl;
	}

	public String getSerchAutoSuggestUrl() {
		return serchAutoSuggestUrl;
	}

	public void setSerchAutoSuggestUrl(String serchAutoSuggestUrl) {
		this.serchAutoSuggestUrl = serchAutoSuggestUrl;
	}

	public int getNoOfOffers() {
		return noOfOffers;
	}

	public void setNoOfOffers(int noOfOffers) {
		this.noOfOffers = noOfOffers;
	}

	public int getOfferStart() {
		return offerStart;
	}

	public void setOfferStart(int offerStart) {
		this.offerStart = offerStart;
	}

	public int getNoOfBanners() {
		return noOfBanners;
	}

	public void setNoOfBanners(int noOfBanners) {
		this.noOfBanners = noOfBanners;
	}

	public int getBannerStart() {
		return bannerStart;
	}

	public void setBannerStart(int bannerStart) {
		this.bannerStart = bannerStart;
	}


    
    public String getLocalizeUrl() {
		return localizeUrl;
	}

	public void setLocalizeUrl(String localizeUrl) {
		this.localizeUrl = localizeUrl;
	}

	public String getThumborBaseUrl() {
		return thumborBaseUrl;
	}

	public void setThumborBaseUrl(String thumborBaseUrl) {
		this.thumborBaseUrl = thumborBaseUrl;
	}

	public String getThumborBannerUrl() {
		return thumborBannerUrl;
	}

	public void setThumborBannerUrl(String thumborBannerUrl) {
		this.thumborBannerUrl = thumborBannerUrl;
	}

	public String getLocalBaseUrl() {
		return localBaseUrl;
	}

	public void setLocalBaseUrl(String localBaseUrl) {
		this.localBaseUrl = localBaseUrl;
	}

	public String getLocalPDPUrl() {
		return localPDPUrl;
	}

	public void setLocalPDPUrl(String localPDPUrl) {
		this.localPDPUrl = localPDPUrl;
	}

	public int getNumberOfRecords() {
		return numberOfRecords;
	}

	public void setNumberOfRecords(int numberOfRecords) {
		this.numberOfRecords = numberOfRecords;
	}

	public String getBaseUrl() {
        return baseUrl;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public String getTrendingUrl() {
        return trendingUrl;
    }

    public void setTrendingUrl(String trendingUrl) {
        this.trendingUrl = trendingUrl;
    }

    public String getBannersUrl() {
        return bannersUrl;
    }

    public void setBannersUrl(String bannersUrl) {
        this.bannersUrl = bannersUrl;
    }

    public String getCategoryUrl() {
        return categoryUrl;
    }

    public void setCategoryUrl(String categoryUrl) {
        this.categoryUrl = categoryUrl;
    }

	public String getAssetVersion() {
		return assetVersion;
	}

	public void setAssetVersion(String assetVersion) {
		this.assetVersion = assetVersion;
	}

	public String getDynamicFilterUrl() {
		return dynamicFilterUrl;
	}

	public void setDynamicFilterUrl(String dynamicFilterUrl) {
		this.dynamicFilterUrl = dynamicFilterUrl;
	}

	public String getCategoryProductsUrl() {
		return categoryProductsUrl;
	}

	public void setCategoryProductsUrl(String categoryProductsUrl) {
		this.categoryProductsUrl = categoryProductsUrl;
	}

	public String getFilterUrl() {
		return filterUrl;
	}

	public void setFilterUrl(String filterUrl) {
		this.filterUrl = filterUrl;
	}

	public int getCategoryTreeDepth() {
		return categoryTreeDepth;
	}

	public void setCategoryTreeDepth(int categoryTreeDepth) {
		this.categoryTreeDepth = categoryTreeDepth;
	}

	public String getOffersUrl() {
		return offersUrl;
	}

	public void setOffersUrl(String offersUrl) {
		this.offersUrl = offersUrl;
	}

	public String getSortCategoryUrl() {
		return sortCategoryUrl;
	}

	public void setSortCategoryUrl(String sortCategoryUrl) {
		this.sortCategoryUrl = sortCategoryUrl;
	}
    
	public String getRefreshProductDetailsUrl() {
		return refreshProductDetailsUrl;
	}

	public void setRefreshProductDetailsUrl(String refreshProductDetailsUrl) {
		this.refreshProductDetailsUrl = refreshProductDetailsUrl;
	}

	public String getVerifyPinCodeUrl() {
		return verifyPinCodeUrl;
	}

	public void setVerifyPinCodeUrl(String verifyPinCodeUrl) {
		this.verifyPinCodeUrl = verifyPinCodeUrl;
	}

	public String getSimilarProductsUrl() {
        return similarProductsUrl;
    }

    public void setSimilarProductsUrl(String similarProductsUrl) {
        this.similarProductsUrl = similarProductsUrl;
    }

    public String getThumborWebpUrl() {
        return thumborWebpUrl;
    }

    public void setThumborWebpUrl(String thumborWebpUrl) {
        this.thumborWebpUrl = thumborWebpUrl;
    }

    public String getThumborIosUrl() {
        return thumborIosUrl;
    }

    public void setThumborIosUrl(String thumborIosUrl) {
        this.thumborIosUrl = thumborIosUrl;
    }

}
