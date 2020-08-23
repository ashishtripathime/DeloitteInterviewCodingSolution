package com.interview.deloitte.UrlShortening;

import com.interview.deloitte.UrlShortening.api.UrlOperation;
import com.interview.deloitte.UrlShortening.impl.UrlOperationImpl;

/**
 * Main method mocking app class
 *
 */
public class App {
	public static void main(String[] args) {
		final String URL = "https://www.ashish.com/interview/url/shorten/api/";

		UrlOperation urlOperation = new UrlOperationImpl();

		String shortUrl = urlOperation.shortenUrl(URL);

		System.out.println("Shorten Url : " + shortUrl);

		String originalUrl = urlOperation.getActualUrl(shortUrl);

		System.out.println("Actual Url : " + originalUrl);

	}
}
