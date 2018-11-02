package com.john.bryce.df.util.fcm.message;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.data.entity.User;
import com.john.bryce.df.util.fcm.MessageType;

@XmlRootElement
public final class FcmUserMessage extends FcmMessage {
	
	@JsonProperty(value = "user")
	private User user;
	
	public FcmUserMessage() {
		super(MessageType.USER_ADDED);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return super.toString() + " FcmUserMessage [user=" + user + "]";
	}
	
	
}
