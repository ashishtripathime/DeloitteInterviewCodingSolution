package com.interview.deloitte.UrlShortening.exception;

/**
 * Custom Exception class for handling exception in URL shortening application
 *
 */
public class UrlShorteningException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UrlShorteningException(String reason) {
		super(reason);
	}

}
