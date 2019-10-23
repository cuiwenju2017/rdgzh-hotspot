package com.shanjing.hr.bean;

/**
 * 姓名
 */
public class HRNameEvent {
    private final String message;

    public HRNameEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
