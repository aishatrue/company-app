package com.group.companyapp.dto.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
        this.todayDate = workStart;
        Date date;
        try {
            date = new SimpleDateFormat("yyyyMMddHHmmss").parse(todayDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        todayDate = new SimpleDateFormat("yyyy-MM-dd").format(date);

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
