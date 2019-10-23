package com.shanjing.hr.bean;

/**
 * 项目名称
 */
public class HRProjectNameEvent {
    private final String message;

    public HRProjectNameEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
