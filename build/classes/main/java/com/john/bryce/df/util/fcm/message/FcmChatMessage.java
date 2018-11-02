package com.john.bryce.df.util.fcm.message;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.data.entity.Chat;
import com.john.bryce.df.util.fcm.MessageType;

@XmlRootElement
public final class FcmChatMessage extends FcmMessage {

	@JsonProperty(value = "chat")
	private Chat chat;

	public FcmChatMessage() {
		super(MessageType.CHAT_MESSAGE);
	}

	public Chat getChat() {
		return chat;
	}

	public void setChat(Chat chat) {
		this.chat = chat;
	}

	@Override
	public String toString() {
		return super.toString() + " FcmChatMessage [chat=" + chat + "]";
	}
	
}
