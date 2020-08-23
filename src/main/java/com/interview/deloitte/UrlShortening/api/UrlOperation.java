package com.interview.deloitte.UrlShortening.api;

public interface UrlOperation {

	String shortenUrl(String actaulUrl);

	String getActualUrl(String shortUrl);

}