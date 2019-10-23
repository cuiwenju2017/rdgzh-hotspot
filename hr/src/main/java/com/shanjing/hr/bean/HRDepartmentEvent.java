package com.shanjing.hr.bean;

/**
 * 所属部门
 */
public class HRDepartmentEvent {
    private final String message;

    public HRDepartmentEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
