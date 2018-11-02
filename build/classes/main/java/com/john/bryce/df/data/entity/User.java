package com.john.bryce.df.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public final class User {

	public static final String TABLE_NAME = "USERS";
	public static final String COLUMN_ID = "USER_ID";
	public static final String COLUMN_NAME = "USER_NAME";
	public static final String COLUMN_FCM_TOKEN = "USER_FCM_TOKEN";
	public static final String COLUMN_IMAGE_URL = "USER_IMAGE_URL";
	public static final String COLUMN_SCORE = "USER_SCORE";

	@JsonProperty(value = "id")
	private String id;
	@JsonProperty(value = "name")
	private String name;
	@JsonProperty(value = "fcm_token")
	private String fcmToken;
	@JsonProperty(value = "image_url")
	private String imageUrl;
	@JsonProperty(value = "score")
	private int score;

	public User() {
	}

	public User(String id, String name, String fcmToken, String imageUrl, int score) {
		this.id = id;
		this.name = name;
		this.fcmToken = fcmToken;
		this.imageUrl = imageUrl;
		this.score = score;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFcmToken() {
		return fcmToken;
	}

	public void setFcmToken(String fcmToken) {
		this.fcmToken = fcmToken;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", fcmToken=" + fcmToken + ", imageUrl=" + imageUrl + ", score="
				+ score + "]";
	}

}
