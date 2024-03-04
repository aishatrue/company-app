package com.group.companyapp.dto.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GetWorkTimeRequest {

    private Long workerId;

    private String yearMonth;

    public GetWorkTimeRequest(Long workerId, String yearMonth) {
        this.workerId = workerId;
        this.yearMonth = yearMonth;
    }
    public GetWorkTimeRequest(){

    }

    public Long getWorkerId() {
        return workerId;
    }

    public String getYearMonth() {



        return yearMonth;
    }
}
