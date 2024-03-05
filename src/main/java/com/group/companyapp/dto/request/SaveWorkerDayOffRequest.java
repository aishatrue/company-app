package com.group.companyapp.dto.request;

public class SaveWorkerDayOffRequest {

    private Long workerId;
    private String dayOffDate;

    public SaveWorkerDayOffRequest(Long workerId, String dayOffDate) {
        this.workerId = workerId;
        this.dayOffDate = dayOffDate;
    }

    public SaveWorkerDayOffRequest(){

    }

    public Long getWorkerId() {
        return workerId;
    }

    public String getDayOffDate() {
        return dayOffDate;
    }
}
