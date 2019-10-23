package com.shanjing.hr.bean;

/**
 * 公司电话
 */
public class HRCompanyPhoneEvent {
    private final String message;

    public HRCompanyPhoneEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
