package com.john.bryce.df.util.json.androidcanvas;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonProperty;

@XmlRootElement
public class PointList {

    @JsonProperty(value = "message")
    private List<Point> message;
    @JsonProperty(value = "viewHeight")
    private int viewHeight;
    @JsonProperty(value = "viewWidth")
    private int viewWidth;

    public PointList() {
    }


    public List<Point> getMessage() {
        return message;
    }

    public void setMessage(List<Point> message) {
        this.message = message;
    }

    public PointList(List<Point> message, int viewHeight, int viewWidth) {
        this.message = message;
        this.viewHeight = viewHeight;
        this.viewWidth = viewWidth;
    }

    public int getViewHeight() {
        return viewHeight;
    }

    public void setViewHeight(int viewHeight) {
        this.viewHeight = viewHeight;
    }

    public int getViewWidth() {
        return viewWidth;
    }

    public void setViewWidth(int viewWidth) {
        this.viewWidth = viewWidth;
    }

    @Override
    public String toString() {
        return "PointList{" +
                "message=" + message +
                ", viewHeight=" + viewHeight +
                ", viewWidth=" + viewWidth +
                '}';
    }
}
