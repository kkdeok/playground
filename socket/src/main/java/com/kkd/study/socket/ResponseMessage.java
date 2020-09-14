package com.kkd.study.socket;

/**
 * Created by Kideok Kim on 25/06/2018.
 */
public enum  ResponseMessage {
    HELLO_CLIENT("HELLO CLIENT"),
    UNEXPECTED("UNEXPECTED REQUEST");

    private final String message;

    ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
