package com.group.companyapp.dto.response;

public class TeamResponse {
  private String teamName;
  private String manager;
  private int memberCount;

    public TeamResponse(String teamName, String manager, Integer memberCount) {
        this.teamName = teamName;
        this.manager = manager;
        this.memberCount = memberCount;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getManager() {
        return manager;
    }

    public int getMemberCount() {
        return memberCount;
    }
}