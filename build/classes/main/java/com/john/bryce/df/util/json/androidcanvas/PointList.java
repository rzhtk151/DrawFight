package com.john.bryce.df.util.json.androidcanvas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class PointList {

	@JsonProperty(value = "message")
	private List<Point> message;
	@JsonProperty(value = "view_size")
	private int viewSize;

	public PointList() {
	}

	public PointList(List<Point> message, int viewSize) {
		this.message = message;
		this.viewSize = viewSize;
	}

	public List<Point> getMessage() {
		return message;
	}

	public void setMessage(List<Point> message) {
		this.message = message;
	}

	public int getViewSize() {
		return viewSize;
	}

	public void setViewSize(int viewSize) {
		this.viewSize = viewSize;
	}

	@Override
	public String toString() {
		return "PointList [message=" + message + ", viewSize=" + viewSize + "]";
	}
}
