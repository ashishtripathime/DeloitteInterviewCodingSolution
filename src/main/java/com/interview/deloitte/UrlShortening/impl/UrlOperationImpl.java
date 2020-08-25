package com.interview.deloitte.UrlShortening.impl;

import java.util.Base64;

import com.interview.deloitte.UrlShortening.api.UrlOperation;
import com.interview.deloitte.UrlShortening.dao.UrlShorteningDB;
import com.interview.deloitte.UrlShortening.exception.UrlShorteningException;

public class UrlOperationImpl implements UrlOperation {

	private static Integer COUNTER = 100;

	private static final String UTF8 = "UTF-8";

	private static final String SHORT_PRETEXT_URL = "dtl.yl/";

	public String shortenUrl(String actualUrl) {
		actualUrl = actualUrl.trim();
		Integer localId = new Integer(COUNTER);
		String encodedValueCounter = new String();
		try {

			if (UrlShorteningDB.containsUrl(actualUrl)) {
				localId = new Integer(UrlShorteningDB.readDB(actualUrl));
			} else {
				UrlShorteningDB.writeDB(actualUrl, localId);
				COUNTER++;
			}
			encodedValueCounter = Base64.getEncoder().encodeToString(localId.toString().getBytes(UTF8));

		} catch (Exception ex) {
			throw new UrlShorteningException("Application encountered error while shortening URL: \r\n " + actualUrl);
		}
		return SHORT_PRETEXT_URL + encodedValueCounter;

	}

	public String getActualUrl(String shortUrl) {
		String actualUrl = "";
		try {
			String encodedValue = shortUrl.substring(SHORT_PRETEXT_URL.length());
			byte[] decodedArray = Base64.getDecoder().decode(encodedValue);
			Integer decodeValue = new Integer(new String(decodedArray, UTF8));
			actualUrl = UrlShorteningDB.getUrlBasedOnId(decodeValue);
		} catch (Exception ex) {
			throw new UrlShorteningException("Application encountered error while shortening URL: \r\n " + actualUrl);
		}

		return actualUrl;
	}

}