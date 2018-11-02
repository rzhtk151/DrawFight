package com.john.bryce.df.util.fcm.message;

import javax.xml.bind.annotation.XmlRootElement;

import com.john.bryce.df.util.json.PushNextQuestion;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import com.john.bryce.df.util.fcm.MessageType;

@XmlRootElement
public final class FcmQuestionMessage extends FcmMessage {

    @JsonProperty(value = "next_question")
    private PushNextQuestion pushNextQuestion;

    public FcmQuestionMessage() {
        super(MessageType.NEXT_QUESTION_READY);
    }

    public PushNextQuestion getPushNextQuestion() {
        return pushNextQuestion;
    }

    public void setPushNextQuestion(PushNextQuestion pushNextQuestion) {
        this.pushNextQuestion = pushNextQuestion;
    }

    @Override
    public String toString() {
        return "FcmQuestionMessage{" +
                "pushNextQuestion=" + pushNextQuestion +
                '}';
    }
}
