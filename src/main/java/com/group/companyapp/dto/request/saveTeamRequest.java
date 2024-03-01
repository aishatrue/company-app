package com.group.companyapp.dto.request;

public class saveTeamRequest {
    private String name;
    private String manager;
    private Integer memberCount;

    public saveTeamRequest(String name, String manager, Integer memberCount) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
    }
    public saveTeamRequest(){

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
