package com.group.companyapp.dto.request;

import java.security.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class SaveAttendanceRequest {
    private Long id;
    private LocalDate workStart;
    private boolean isWorking;
//    private LocalDate todayDate;

    private Long workerId;


    public SaveAttendanceRequest(Long id, LocalDate workStart, boolean isWorking,Long workerId) {
        this.id = id;
        this.workStart = workStart;
        this.isWorking = isWorking;
        this.workerId = workerId;
    }
    public SaveAttendanceRequest(){

    }

    public Long getId() {
        return id;
    }

//    public LocalDate getTodayDate() {
//        return todayDate;
//    }

    public LocalDate getWorkStart() {
        return workStart;
    }

    public boolean isWorking() {
        return isWorking;
    }

    public Long getWorkerId() {
        return workerId;
    }
}
