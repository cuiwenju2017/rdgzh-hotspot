package com.shanjing.hr.bean;

/**
 * 专业
 */
public class HRMajorEvent {
    private final String message;

    public HRMajorEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
