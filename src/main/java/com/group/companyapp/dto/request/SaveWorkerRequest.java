package com.group.companyapp.dto.request;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static java.lang.Integer.parseInt;

public class SaveWorkerRequest {


    private String name;

    private  String teamName;


    private  String role;


    private LocalDate birthday;


    private LocalDate workStartDate;

    private Long dayOff;

    public SaveWorkerRequest(String name, String teamName, String role, LocalDate birthday, LocalDate workStartDate) {
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
    }
    public SaveWorkerRequest(){

    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getRole() {
        return role;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public LocalDate getWorkStartDate() {
        return workStartDate;
    }

    public Long getDayOff() {
        int startYear = workStartDate.getYear();
        Date today = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
        //원하는 데이터 포맷 지정
        String strNowDate = simpleDateFormat.format(today);
        

        if(parseInt(strNowDate)==startYear){
            return 88L;
        }else{
            return 120L;
        }

    }
}
