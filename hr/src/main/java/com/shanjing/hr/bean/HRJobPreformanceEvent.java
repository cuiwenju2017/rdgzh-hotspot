package com.shanjing.hr.bean;

/**
 * 工作业绩
 */
public class HRJobPreformanceEvent {
    private final String message;

    public HRJobPreformanceEvent(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
