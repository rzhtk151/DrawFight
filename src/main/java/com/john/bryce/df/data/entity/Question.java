package com.john.bryce.df.data.entity;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public final class Question {

	public static final String TABLE_NAME = "QUESTIONS";
	public static final String COLUMN_ID = "QUESTION_ID";
	public static final String COLUMN_WORD = "QUESTION_WORD";
	
	@JsonProperty(value = "id")
	private long id;
	@JsonProperty(value = "word")
	private String word;

	public Question() {
	}

	public Question(long id, String word) {
		this.id = id;
		this.word = word;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", word=" + word + "]";
	}

}
