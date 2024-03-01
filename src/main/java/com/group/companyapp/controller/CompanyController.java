package com.group.companyapp.controller;

import com.group.companyapp.dto.request.saveTeamRequest;
import com.group.companyapp.service.CompanyService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/team/save")
    public void saveTeam(@RequestBody saveTeamRequest request){
        companyService.saveTeam(request);

    }



}
