package com.group.companyapp.dto.response;

import java.util.List;

public class FinalAttendanceResponse {
    private List<AttendanceResponse> detail;
    private long sum;

    public FinalAttendanceResponse(long sum, List<AttendanceResponse> detail) {
        this.detail = detail;
        this.sum = sum;
    }

    public List<AttendanceResponse> getDetail() {
        return detail;
    }

    public long getSum() {
        return sum;
    }
}
