package com.john.bryce.df.util.fcm;

import java.util.List;

import com.john.bryce.df.data.DbHandler;
import com.john.bryce.df.data.entity.User;
import com.john.bryce.df.util.HttpUtils;
import com.john.bryce.df.util.JsonUtils;
import com.john.bryce.df.util.fcm.message.FcmMessage;

public final class FcmSender {
	
	private FcmSender() {
	}
	
	public static void sendToAll(FcmMessage fcmMessage) {
		final List<User> users = DbHandler.getInstance().getUserDao().getAll();
		sendTo(users, fcmMessage);
	}
	
	public static void sendToAllExcept(FcmMessage fcmMessage, String idNotToSendTo) {
		final List<User> users = DbHandler.getInstance().getUserDao().getAllExcept(idNotToSendTo);
		sendTo(users, fcmMessage);
	}
	
	private static void sendTo(List<User> users, FcmMessage fcmMessage) {
		final FcmPackage fcmPackage = new FcmPackage();
		fcmPackage.setMessage(fcmMessage);
		for (final User user : users) {
			fcmPackage.setReceiverToken(user.getFcmToken());
			send(fcmPackage);
		}
	}
	
	public static void send(FcmPackage fcmPackage) {
		final String json = JsonUtils.toJson(fcmPackage);
		HttpUtils.postToFcm(json);
	}
}
