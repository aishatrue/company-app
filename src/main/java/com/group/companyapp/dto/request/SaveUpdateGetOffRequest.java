package com.group.companyapp.dto.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SaveUpdateGetOffRequest {

    private Long id;


    private String workEnd;
    private boolean workState;
    private String todayDate;

    private Long workerId;

    //이 생성자를 불러서 값 초기화를 하고 getter로 받아야 됨.
    public SaveUpdateGetOffRequest(String workEnd, boolean workState,Long workerId) {
        this.workEnd = workEnd;
        this.workState = workState;
        this.todayDate = workEnd;
        this.workerId = workerId;
    }
    public SaveUpdateGetOffRequest(){

    }

    public Long getId() {
        return id;
    }

    public String getWorkEnd() {
        return workEnd;
    }

    public boolean isWorkState() {
        return workState;
    }

    //핵삼 메소드: postman으로 받아온 문자열을 먼저 date타입으로 파싱해야함. 그래야 format을 바꾸는 함수르 쓸 수 있음.단,보내준 데이터랑 동일한 포맷으로 적어줘야함.
    //그 다음 원하는 년월일 포맷으로 바꿔주고 그걸 리턴.
    public String getTodayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDateTime;
        Date test;

        try {
           test = new SimpleDateFormat("yyyyMMddHHmmss").parse(workEnd);
            formattedDateTime = dateFormat.format(test);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }


        this.todayDate = formattedDateTime;
        return todayDate;
    }

    public Long getWorkerId() {
        return workerId;
    }
}
