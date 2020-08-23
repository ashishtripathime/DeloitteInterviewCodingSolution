package com.interview.deloitte.UrlShortening.impl;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.modules.junit4.PowerMockRunner;

import com.interview.deloitte.UrlShortening.api.UrlOperation;

@RunWith(PowerMockRunner.class)
public class UrlOperationImplTest {

	@InjectMocks
	UrlOperation urlOperation = new UrlOperationImpl();

	@Test
	public void testUrlShortening() {

		String ExpectedUrl = "https:/www.test.com/api/shorten/url/";

		String shortUrl = urlOperation.shortenUrl(ExpectedUrl);

		Assert.assertThat(shortUrl, CoreMatchers.not(ExpectedUrl));

		String actualUrl = urlOperation.getActualUrl(shortUrl);

		Assert.assertThat(actualUrl, CoreMatchers.is(ExpectedUrl));
	}
}