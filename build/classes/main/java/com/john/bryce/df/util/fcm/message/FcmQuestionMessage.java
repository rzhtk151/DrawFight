package com.john.bryce.df.util.fcm.message;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.util.fcm.MessageType;

@XmlRootElement
public final class FcmQuestionMessage extends FcmMessage {
	
	@JsonProperty(value = "points_to_drawer")
	private int pointsToDrawer;
	@JsonProperty(value = "points_to_answer")
	private int pointsToAnswer;
	
	public FcmQuestionMessage() {
		super(MessageType.NEXT_QUESTION_READY);
	}

	public int getPointsToDrawer() {
		return pointsToDrawer;
	}

	public void setPointsToDrawer(int pointsToDrawer) {
		this.pointsToDrawer = pointsToDrawer;
	}

	public int getPointsToAnswer() {
		return pointsToAnswer;
	}

	public void setPointsToAnswer(int pointsToAnswer) {
		this.pointsToAnswer = pointsToAnswer;
	}

	@Override
	public String toString() {
		return super.toString() + " FcmQuestionMessage [pointsToDrawer=" + pointsToDrawer + ", pointsToAnswer=" + pointsToAnswer + "]";
	}
}
