package com.group.companyapp.dto.response;

public class TeamResponse {
  private String teamName;
  private Integer memberCount;



    public TeamResponse(String teamName, Integer memberCount) {
        this.teamName = teamName;
        this.memberCount = memberCount;
    }

    public String getTeamName() {
        return teamName;
    }

    public Integer getMemberCount() {
        return memberCount;
    }


}
