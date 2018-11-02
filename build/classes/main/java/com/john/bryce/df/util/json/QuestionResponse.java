package com.john.bryce.df.util.json;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.data.entity.Question;

@XmlRootElement
public final class QuestionResponse {

	@JsonProperty(value = "error_details")
	private ErrorDetails errorDetails;
	@JsonProperty(value = "questions")
	private List<Question> questions = new ArrayList<Question>(0);

	public QuestionResponse() {
	}

	public QuestionResponse(ErrorDetails errorDetails, List<Question> questions) {
		this.errorDetails = errorDetails;
		this.questions = questions;
	}

	public ErrorDetails getErrorDetails() {
		return errorDetails;
	}

	public void setErrorDetails(ErrorDetails errorDetails) {
		this.errorDetails = errorDetails;
	}

	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public void addQuestion(Question question) {
		this.questions.add(question);
	}
	
	@Override
	public String toString() {
		return "QuestionResponse [errorDetails=" + errorDetails + ", questions=" + questions + "]";
	}

}
