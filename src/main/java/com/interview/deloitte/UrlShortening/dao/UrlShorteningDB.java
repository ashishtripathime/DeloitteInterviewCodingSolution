package com.interview.deloitte.UrlShortening.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class UrlShorteningDB {
	private static Map<String, Integer> ID_URL_MAP = new HashMap<String, Integer>();

	public static boolean writeDB(String actualUrl, Integer id) {
		ID_URL_MAP.put(actualUrl.trim(), id);
		return true;
	}

	public static Integer readDB(String actualUrl) {
		return ID_URL_MAP.get(actualUrl.trim());
	}

	public static boolean containsUrl(String url) {
		return ID_URL_MAP.containsKey(url.trim());
	}

	public static String getUrlBasedOnId(Integer id) {
		String url = "";
		Iterator<Map.Entry<String, Integer>> iterator = ID_URL_MAP.entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Integer> entry = iterator.next();
			if (entry.getValue().equals(id)) {
				url = entry.getKey();
				break;
			}
		}
		return url;

	}
}
