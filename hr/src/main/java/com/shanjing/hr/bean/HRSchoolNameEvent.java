package com.shanjing.hr.bean;

/**
 * 学校
 */
public class HRSchoolNameEvent {
    private final String message;

    public HRSchoolNameEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
