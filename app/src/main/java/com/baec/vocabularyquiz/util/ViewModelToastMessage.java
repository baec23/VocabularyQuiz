package com.baec.vocabularyquiz.util;

public class ViewModelToastMessage {
    private Type type;
    private int messageStringId;

    public ViewModelToastMessage(Type type, int messageStringId) {
        this.type = type;
        this.messageStringId = messageStringId;
    }

    public Type getType() {
        return type;
    }

    public int getMessageStringId() {
        return messageStringId;
    }

    public enum Type {
        ERROR,
        MESSAGE
    }
}
