package com.shanjing.hr.bean;

/**
 * 联系方式
 */
public class HRContactWayEvent {
    private final String message;

    public HRContactWayEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
