package com.john.bryce.df.util.json;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public final class ErrorDetails {

	public static ErrorDetails success() {
		return new ErrorDetails(0, "SUCCESS", "");
	}

	@JsonProperty(value = "error_code")
	private int errorCode;
	@JsonProperty(value = "error_message")
	private String errorMessage;
	@JsonProperty(value = "error_note")
	private String errorNote;

	public ErrorDetails(int errorCode, String errorMessage, String errorNote) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorNote = errorNote;
	}

	public ErrorDetails() {
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public String getErrorNote() {
		return errorNote;
	}

	public void setErrorNote(String errorNote) {
		this.errorNote = errorNote;
	}

	@Override
	public String toString() {
		return "ErrorDetails [errorCode=" + errorCode + ", errorMessage=" + errorMessage + ", errorNote=" + errorNote
				+ "]";
	}

}
