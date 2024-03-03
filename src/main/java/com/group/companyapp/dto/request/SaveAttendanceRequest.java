package com.group.companyapp.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class SaveAttendanceRequest {
    private Long id;


    private String workStart;
    private boolean workState;
    private String todayDate;

    private Long workerId;


    public SaveAttendanceRequest(Long id, String workStart, boolean workState,Long workerId) {
        this.id = id;
        this.workStart = workStart;
        this.workState = workState;
        this.workerId = workerId;
        this.todayDate = workStart;
    }
    public SaveAttendanceRequest(){

    }

    public Long getId() {
        return id;
    }

    public String getTodayDate() {
        return todayDate;
    }

    public String getWorkStart() {
        return workStart;
    }

    public boolean isWorkState() {
        return workState;
    }

    public Long getWorkerId() {
        return workerId;
    }
}
