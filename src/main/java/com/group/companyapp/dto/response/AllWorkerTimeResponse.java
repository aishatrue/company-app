package com.group.companyapp.dto.response;

public class AllWorkerTimeResponse {
    private Long workerId;
    private Long workingMinutes;

    public AllWorkerTimeResponse(Long workerId, Long workingMinutes) {
        this.workerId = workerId;
        this.workingMinutes = workingMinutes;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public Long getWorkingMinutes() {
        return workingMinutes;
    }
}
