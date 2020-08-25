package com.interview.deloitte.UrlShortening.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.interview.deloitte.UrlShortening.api.UrlOperation;
import com.interview.deloitte.UrlShortening.dao.UrlShorteningDB;
import com.interview.deloitte.UrlShortening.exception.UrlShorteningException;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UrlShorteningDB.class)
public class UrlOperationImplTest {

	UrlOperation urlOperation;

	@Before
	public void init() {
		urlOperation = new UrlOperationImpl();
	}

	@Test
	public void testUrlShortening() {

		String ExpectedUrl = "https:/www.test.com/api/shorten/url/";

		String shortUrl = urlOperation.shortenUrl(ExpectedUrl);

		Assert.assertThat(shortUrl, CoreMatchers.not(ExpectedUrl));

		String actualUrl = urlOperation.getActualUrl(shortUrl);

		Assert.assertThat(actualUrl, CoreMatchers.is(ExpectedUrl));
	}

	@Test(expected = UrlShorteningException.class)
	public void testShortenUrlFailure() {
		PowerMockito.mockStatic(UrlShorteningDB.class);
		String ExpectedUrl = "https:/www.test.com/api/shorten/url/";
		Mockito.when(UrlShorteningDB.writeDB(Matchers.any(), Matchers.any())).thenThrow(new RuntimeException());

		urlOperation.shortenUrl(ExpectedUrl);
	}

	@Test(expected = UrlShorteningException.class)
	public void testGetActualUrlFailure() {
		PowerMockito.mockStatic(UrlShorteningDB.class);
		String ExpectedUrl = "dtl.yl/MTAw";
		Mockito.when(UrlShorteningDB.getUrlBasedOnId(Matchers.any())).thenThrow(new RuntimeException());

		urlOperation.getActualUrl(ExpectedUrl);
	}
}