package com.group.companyapp.dto.request;

public class SaveTeamRequest {
    private String name;
    private String manager;
    private Integer memberCount;

    private Integer dayOffOption;


    //생성자가 안쓰이니까ㅏ 여기서 if문을 써도 안되지...
    public SaveTeamRequest(String name, String manager, Integer memberCount,Integer dayOffOption) {
        this.name = name;
        this.manager = manager;
        this.memberCount = memberCount;
        this.dayOffOption = dayOffOption;


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

    public Integer getDayOffOption() {

        if(dayOffOption==null){
            dayOffOption = 1;
        }

        return dayOffOption;
    }
}
