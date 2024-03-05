package com.group.companyapp.dto.response;

import java.util.Date;

public class AttendanceResponse {


    private Date date;
    private long workingMinutes;

    private boolean usingDayOff;

    public AttendanceResponse(Date todayDate,long workingMinutes,boolean usingDayOff) {
        this.date = todayDate;
        this.workingMinutes = workingMinutes ;
        this.usingDayOff = usingDayOff;
    }

    public Date getDate() {
        return date;
    }

    public long getWorkingMinutes() {
        return workingMinutes;
    }

    public boolean isUsingDayOff() {
        return usingDayOff;
    }
}
