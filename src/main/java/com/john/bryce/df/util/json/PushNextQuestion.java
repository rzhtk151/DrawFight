package com.john.bryce.df.util.json;

import com.john.bryce.df.util.fcm.MessageType;
import org.codehaus.jackson.annotate.JsonProperty;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement

public class PushNextQuestion {

    @JsonProperty(value = "pointsToDrawer")
    private int pointsToDrawer;
    @JsonProperty(value = "pointsToAnswer")
    private int pointsToAnswer;
    @JsonProperty(value = "answerId")
    private String answerId;
    @JsonProperty(value = "drawerId")
    private String drawerId;
    @JsonProperty(value = "timeStamp")
    private long timeStamp;

    public PushNextQuestion() {
    }

    public PushNextQuestion(int pointsToDrawer, int pointsToAnswer, String answerId, String drawerId, long timeStamp) {
        this.pointsToDrawer = pointsToDrawer;
        this.pointsToAnswer = pointsToAnswer;
        this.answerId = answerId;
        this.drawerId = drawerId;
        this.timeStamp = timeStamp;
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

    public String getAnswerId() {
        return answerId;
    }

    public void setAnswerId(String answerId) {
        this.answerId = answerId;
    }

    public String getDrawerId() {
        return drawerId;
    }

    public void setDrawerId(String drawerId) {
        this.drawerId = drawerId;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public String toString() {
        return "PushNextQuestion{" +
                "pointsToDrawer=" + pointsToDrawer +
                ", pointsToAnswer=" + pointsToAnswer +
                ", answerId='" + answerId + '\'' +
                ", drawerId='" + drawerId + '\'' +
                ", timeStamp=" + timeStamp +
                '}';
    }
}
