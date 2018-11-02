package com.john.bryce.df.util.json;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.util.json.androidcanvas.PointList;

@XmlRootElement
public final class PushDrawRequest {

	@JsonProperty(value = "point_list")
	private PointList pointList;
	@JsonProperty(value = "user_id")
	private String userId;

	public PushDrawRequest(PointList pointList, String userId) {
		this.pointList = pointList;
		this.userId = userId;
	}

	public PushDrawRequest() {
	}
	
	public PointList getPointList() {
		return pointList;
	}

	public void setPointList(PointList pointList) {
		this.pointList = pointList;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "PushDrawRequest [pointList=" + pointList + ", userId=" + userId + "]";
	}

}
