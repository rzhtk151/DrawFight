package com.john.bryce.df.util.fcm;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.util.fcm.message.FcmMessage;

public final class FcmPackage {

	@JsonProperty(value = "data")
	private FcmMessage message;
	@JsonProperty(value = "to")
	private String receiverToken;

	public FcmPackage() {
	}

	public FcmPackage(FcmMessage message, String receiverToken) {
		this.message = message;
		this.receiverToken = receiverToken;
	}

	public FcmMessage getMessage() {
		return message;
	}

	public void setMessage(FcmMessage message) {
		this.message = message;
	}

	public String getReceiverToken() {
		return receiverToken;
	}

	public void setReceiverToken(String receiverToken) {
		this.receiverToken = receiverToken;
	}

	@Override
	public String toString() {
		return "FcmMessage [message=" + message + ", receiverToken=" + receiverToken + "]";
	}
}
