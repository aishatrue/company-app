package com.group.companyapp.domain;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private  String role;

    @Column(nullable = false)
    private LocalDate birthday;

    @Column(nullable = false)
    private LocalDate workStartDate;

    private  String teamName;

    private Long dayOff;




    public Worker(String name, String teamName, String role, LocalDate birthday, LocalDate workStartDate,Long dayOff) {
        this.name = name;
        this.teamName = teamName;
        this.role = role;
        this.birthday = birthday;
        this.workStartDate = workStartDate;
        this.dayOff = dayOff;

    }

    public Worker(){

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
        return dayOff;
    }
    public void updateGetoff(){
        this.dayOff = dayOff - 8 ;
    }
}
