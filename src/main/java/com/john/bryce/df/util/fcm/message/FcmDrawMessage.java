package com.john.bryce.df.util.fcm.message;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.util.fcm.MessageType;
import com.john.bryce.df.util.json.androidcanvas.PointList;

@XmlRootElement
public final class FcmDrawMessage extends FcmMessage {
	
	@JsonProperty(value = "point_list")
	private PointList pointList;
	
	public FcmDrawMessage() {
		super(MessageType.CANVAS_DRAWING);
	}

	public PointList getPointList() {
		return pointList;
	}

	public void setPointList(PointList pointList) {
		this.pointList = pointList;
	}

	@Override
	public String toString() {
		return super.toString() + " FcmDrawMessage [pointList=" + pointList + "]";
	}
}
