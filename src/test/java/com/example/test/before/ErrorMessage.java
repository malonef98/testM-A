package com.example.test.before;

public class ErrorMessage {
    int code;
    String things;

    public ErrorMessage(int code, String things) {
        this.code = code;
        this.things = things;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getThings() {
        return things;
    }

    public void setThings(String things) {
        this.things = things;
    }
}
