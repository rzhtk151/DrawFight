package com.john.bryce.df.util;

import java.awt.*;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public final class HttpUtils {

	public static final String FCM_SERVER_KEY = "AAAAExJdKPQ:APA91bGQgBr6oYI49NnvK_bRww-xnJgJI2aHvHU5XMPF5ftO7bRNzDe4TLhoBXHGhr-ohu42sZa_c6z9PkrEu79LUnIokf3lNXsW0Veu5OHTaqh5RVQ51jdd1V5OcPMrZPSf9Yn98XoN";
	public static final String FCM_MESSAGE_URL = "https://fcm.googleapis.com/fcm/send";

	private static final MediaType MEDIATYPE_JSON = MediaType.parse("application/json; charset=utf-8");
	private static final OkHttpClient client = new OkHttpClient();

	public static void postToFcm(final String json) {
		try {
			postToFcmNoTry(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void postToFcmNoTry(final String json) throws IOException {
		final RequestBody requestBody = RequestBody.create(MEDIATYPE_JSON, json);
		final Request request = new Request.Builder()
				.url(FCM_MESSAGE_URL)
				.addHeader("Content-Type", "application/json")
				.addHeader("Authorization", "key=" + FCM_SERVER_KEY)
				.post(requestBody)
				.build();
		final Response response = client.newCall(request).execute();
		response.close();
	}

	private HttpUtils() {
	}
}
