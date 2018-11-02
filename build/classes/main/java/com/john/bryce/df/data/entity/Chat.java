package com.john.bryce.df.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public final class Chat {

	public static final String TABLE_NAME = "CHAT";
	public static final String COLUMN_ID = "CHAT_ID";
	public static final String COLUMN_USER_ID = "CHAT_USER_ID";
	public static final String COLUMN_MESSAGE = "CHAT_MESSAGE";
	public static final String COLUMN_TIME_STAMP = "CHAT_TIME_STAMP";

	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "user_id")
	private String userId;
	@JsonProperty(value = "message")
	private String message;
	@JsonProperty(value = "time_stamp")
	private long timeStamp;

	public Chat() {
	}

	public Chat(long id, String userId, String message, long timeStamp) {
		this.id = id;
		this.userId = userId;
		this.message = message;
		this.timeStamp = timeStamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "Chat [id=" + id + ", userId=" + userId + ", message=" + message + ", timeStamp=" + timeStamp + "]";
	}

}
