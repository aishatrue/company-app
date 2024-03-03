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

//    private LocalDate todayDate;
    private LocalDate workStart;




    private LocalDate workEnd;

    @Column(name="is_working")
    private boolean isWorking;

    public Attendance(Long workerId, LocalDate workStart, LocalDate workEnd, boolean isWorking) {
        this.workerId = workerId;
      this.workStart =workStart;

        this.workEnd = workEnd;
        this.isWorking = isWorking;
    }

    public Long getId() {
        return id;
    }

    public Long getWorkerId() {
        return workerId;
    }

//    public LocalDate getTodayDate() {
//        return todayDate;
//    }

    public LocalDate getWorkStart() {
        return workStart;
    }





    public LocalDate getWorkEnd() {
        return workEnd;
    }

    public boolean isWorking() {
        return isWorking;
    }
}
