package com.shanjing.hr.bean;

/**
 * 融资状况
 */
public class HRFinancingEvent {
    private final String message;

    public HRFinancingEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
