package com.group.companyapp.domain;

import javax.persistence.*;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workerId;

    private String todayDate;
    private String workStart;


    private String workEnd;

    @Column(name="work_state")
    private boolean workState;

    private boolean usingDayOff;

    public Attendance(Long workerId, String todayDate,String workStart, String workEnd, boolean workState,boolean usingDayOff) {
        this.workerId = workerId;

        this.workStart = workStart;
        this.todayDate = workStart;

        this.workEnd = workEnd;
        this.workState = workState;
        this.usingDayOff = usingDayOff;
    }

    public Attendance() {
    }

    public Long getId() {
        return id;
    }

    public Long getWorkerId() {
        return workerId;
    }

    public String getTodayDate() {
        return todayDate;
    }


    public String getWorkStart() {
        return workStart;
    }


    public String getWorkEnd() {
        return workEnd;
    }

    public boolean isWorkState() {
        return workState;
    }

    public void updateAttendance(String workEnd,boolean workState){
        this.workEnd = workEnd;
        this.workState = workState;
    }
}
