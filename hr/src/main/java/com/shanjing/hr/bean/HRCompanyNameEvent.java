package com.shanjing.hr.bean;

/**
 * 公司名称
 */
public class HRCompanyNameEvent {
    private final String message;

    public HRCompanyNameEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
