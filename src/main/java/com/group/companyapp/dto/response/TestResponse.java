package com.group.companyapp.dto.response;

public class TestResponse {
    private String teamName;
    private String name;
    private Integer memberCount;

    public TestResponse(String teamName,String name, Integer memberCount) {
        this.name = name;
        this.teamName = teamName;
        this.memberCount = memberCount;
    }

    public String getName() {
       return name;
    }

    public String getTeamName() {
        return teamName;
    }


    public Integer getMemberCount() {
        return memberCount;
    }
}
