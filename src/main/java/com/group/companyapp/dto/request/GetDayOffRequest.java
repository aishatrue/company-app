package com.group.companyapp.dto.request;

public class GetDayOffRequest {
    private Long workerId;

    public GetDayOffRequest(Long workerId) {
        this.workerId = workerId;
    }
    public GetDayOffRequest(){

    }

    public Long getWorkerId() {
        return workerId;
    }
}
