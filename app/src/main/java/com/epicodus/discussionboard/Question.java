package com.epicodus.discussionboard;

/**
 * Created by Guest on 7/12/16.
 */
public class Question {
    private String question;
    private String pushId;

    public Question() {}

    public Question(String question) {
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String mPushId) {
        this.pushId = mPushId;
    }

}
