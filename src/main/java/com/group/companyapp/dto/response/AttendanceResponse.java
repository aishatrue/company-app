package com.group.companyapp.dto.response;

import java.util.Date;

public class AttendanceResponse {


    private Date date;
    private long workingMinutes;

    public AttendanceResponse(Date todayDate,long workingMinutes) {
        this.date = todayDate;
        this.workingMinutes = workingMinutes ;
    }

    public Date getDate() {
        return date;
    }

    public long getWorkingMinutes() {
        return workingMinutes;
    }
}
