package com.example.myapplication;

public class Message {
    private String text; // notification body
    private MemberData memberData; // data of the App that sent this notification
    private boolean belongsToCurrentUser; // is this message sent by us?

    public Message(String text, MemberData memberData, boolean belongsToCurrentUser) {
        this.text = text;
        this.memberData = memberData;
        this.belongsToCurrentUser = belongsToCurrentUser;
    }

    public String getText() {
        return text;
    }

    public MemberData getMemberData() {
        return memberData;
    }

    public boolean isBelongsToCurrentUser() {
        return belongsToCurrentUser;
    }
}
