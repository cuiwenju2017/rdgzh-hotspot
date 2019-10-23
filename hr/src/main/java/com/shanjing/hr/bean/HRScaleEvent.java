package com.shanjing.hr.bean;

/**
 * 公司规模
 */
public class HRScaleEvent {
    private final String message;

    public HRScaleEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
