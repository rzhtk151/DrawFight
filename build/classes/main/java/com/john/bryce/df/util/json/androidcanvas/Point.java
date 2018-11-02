package com.john.bryce.df.util.json.androidcanvas;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public final class Point {

	@JsonProperty(value = "paint_color")
	private int paintColor;
	@JsonProperty(value = "stroke_width")
	private float strokeWidth;
	@JsonProperty(value = "movement_action")
	private int movementAction;
	@JsonProperty(value = "x")
	private float x;
	@JsonProperty(value = "y")
	private float y;

	public Point() {
	}

	public Point(int paintColor, float strokeWidth, int movementAction, float x, float y) {
		this.paintColor = paintColor;
		this.strokeWidth = strokeWidth;
		this.movementAction = movementAction;
		this.x = x;
		this.y = y;
	}

	public int getPaintColor() {
		return paintColor;
	}

	public void setPaintColor(int paintColor) {
		this.paintColor = paintColor;
	}

	public float getStrokeWidth() {
		return strokeWidth;
	}

	public void setStrokeWidth(float strokeWidth) {
		this.strokeWidth = strokeWidth;
	}

	public int getMovementAction() {
		return movementAction;
	}

	public void setMovementAction(int movementAction) {
		this.movementAction = movementAction;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "Point [paintColor=" + paintColor + ", strokeWidth=" + strokeWidth + ", movementAction=" + movementAction
				+ ", x=" + x + ", y=" + y + "]";
	}
}
