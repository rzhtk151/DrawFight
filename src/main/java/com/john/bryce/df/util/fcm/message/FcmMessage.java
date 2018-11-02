package com.john.bryce.df.util.fcm.message;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.util.fcm.MessageType;

@XmlRootElement
public class FcmMessage {

	@JsonProperty(value = "type")
	private MessageType messageType;

	public FcmMessage() {
	}
	
	public FcmMessage(MessageType messageType) {
		this.messageType = messageType;
	}

	public final MessageType getMessageType() {
		return messageType;
	}
	
	public final void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	@Override
	public String toString() {
		return "FcmMessage [messageType=" + messageType + "]";
	}
	
	
}
