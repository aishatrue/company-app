package com.group.companyapp.dto.request;

public class GetOverTimeRequest {
    private String yearMonth;

    public GetOverTimeRequest(String yearMonth) {
        this.yearMonth = yearMonth;
    }
    public GetOverTimeRequest(){

    }

    public String getYearMonth() {
        return yearMonth;
    }
}
