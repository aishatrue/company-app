package com.group.companyapp.domain;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;

    private String manager;


    @Column(name="member_count")
    private Integer memberCount;


    private Integer dayOffOption;

    public Team(String name, String manager, Integer memberCount,Integer dayOffOption) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
        this.dayOffOption = dayOffOption;
    }
    public Team(){

    }

    public String getName() {
        return name;
    }

    public String getManager() {
        return manager;
    }

    public Integer getMemberCount() {
        return memberCount;
    }

    public Integer getDayOffOption() {
        return dayOffOption;
    }
}
