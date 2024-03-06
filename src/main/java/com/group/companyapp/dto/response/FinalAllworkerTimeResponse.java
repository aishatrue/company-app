package com.group.companyapp.dto.response;

public class FinalAllworkerTimeResponse {
    private Long workerId;
    private String name;
    private Long overTime;

    public FinalAllworkerTimeResponse(Long workerId, String name, Long overTime) {
        this.workerId = workerId;
        this.name = name;
        this.overTime = overTime;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public String getName() {
        return name;
    }

    public Long getOverTime() {
        return overTime;
    }
}
