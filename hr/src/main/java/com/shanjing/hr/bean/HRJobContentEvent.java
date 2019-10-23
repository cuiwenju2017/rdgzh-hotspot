package com.shanjing.hr.bean;

/**
 * 工作内容
 */
public class HRJobContentEvent {
    private final String message;

    public HRJobContentEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
