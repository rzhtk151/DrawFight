package com.john.bryce.df.util.json;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.data.entity.User;

public final class UserResponse {

	@JsonProperty(value = "error_details")
	private ErrorDetails errorDetails;
	@JsonProperty(value = "users")
	private List<User> users = new ArrayList<User>(0);
	@JsonProperty(value = "time_stamp")
	private long timeStamp;

	public UserResponse(ErrorDetails errorDetails, List<User> users, long timeStamp) {
		this.errorDetails = errorDetails;
		this.users = users;
		this.timeStamp = timeStamp;
	}

	public UserResponse() {
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(long timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "UserResponse [errorDetails=" + errorDetails + ", users=" + users + ", timeStamp=" + timeStamp + "]";
	}
}
