package com.john.bryce.df.util.json;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public final class AnswerRequest {

	@JsonProperty(value = "questionId")
	private String questionId;
	@JsonProperty(value = "userId")
	private String userId;
	@JsonProperty(value = "timeStamp")
	private String timeStamp;
	
	public AnswerRequest() {
	}
	
	public AnswerRequest(String questionId, String userId, String timeStamp) {
		this.questionId = questionId;
		this.userId = userId;
		this.timeStamp = timeStamp;
	}

	public String getQuestionId() {
		return questionId;
	}

	public void setQuestionId(String questionId) {
		this.questionId = questionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	@Override
	public String toString() {
		return "AnswerRequest [questionId=" + questionId + ", userId=" + userId + ", timeStamp=" + timeStamp + "]";
	}
	
}
