package com.baec.vocabularyquiz.login;

public class RegistrationStatus {

    private Status status;
    private int messageStringId;

    public RegistrationStatus(Status status, int messageStringId){
        this.status = status;
        this.messageStringId = messageStringId;
    }

    public Status getStatus() {
        return status;
    }

    public int getMessageStringId() {
        return messageStringId;
    }

    public enum Status {
        SUCCESS,
        ERROR
    }
}
