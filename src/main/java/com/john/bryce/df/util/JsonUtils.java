package com.john.bryce.df.util;

import java.io.IOException;

import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.ObjectMapper;

public final class JsonUtils {
	private static final ObjectMapper mapper = new ObjectMapper();

	public static String toJson(Object src) {
		try {
			return mapper.writeValueAsString(src);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private JsonUtils() {
	}
}
