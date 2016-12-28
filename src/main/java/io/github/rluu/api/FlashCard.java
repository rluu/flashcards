package io.github.rluu.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlashCard extends DatabaseObject {
    @JsonProperty
    private String textSide1;
    @JsonProperty
    private String textSide2;
    @JsonProperty
    private String userNotes;

    public String getTextSide1() {
        return textSide1;
    }
    public void setTextSide1(String textSide1) {
        this.textSide1 = textSide1;
    }
    public String getTextSide2() {
        return textSide2;
    }
    public void setTextSide2(String textSide2) {
        this.textSide2 = textSide2;
    }
    public String getUserNotes() {
        return userNotes;
    }
    public void setUserNotes(String userNotes) {
        this.userNotes = userNotes;
    }
}
