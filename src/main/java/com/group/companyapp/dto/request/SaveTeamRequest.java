package com.group.companyapp.dto.request;

public class SaveTeamRequest {
    private String name;
    private String manager;
    private Integer memberCount;

    public SaveTeamRequest(String name, String manager, Integer memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
    }
    public SaveTeamRequest(){

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
}
