package com.group.companyapp.domain;

import javax.persistence.*;
import java.security.Timestamp;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;

@Entity
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long workerId;

    private String todayDate;
    private String workStart;


    private LocalDate workEnd;

    @Column(name="work_state")
    private boolean workState;

    public Attendance(Long workerId, String todayDate,String workStart, LocalDate workEnd, boolean workState) {
        this.workerId = workerId;

        this.workStart = workStart;
        this.todayDate = workStart;

        this.workEnd = workEnd;
        this.workState = workState;
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


    public LocalDate getWorkEnd() {
        return workEnd;
    }

    public boolean isWorkState() {
        return workState;
    }
}
