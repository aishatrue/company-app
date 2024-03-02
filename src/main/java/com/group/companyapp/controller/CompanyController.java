package com.group.companyapp.controller;

import com.group.companyapp.dto.request.SaveTeamRequest;
import com.group.companyapp.dto.request.SaveWorkerRequest;
import com.group.companyapp.dto.response.TeamResponse;
import com.group.companyapp.dto.response.WorkerResponse;
import com.group.companyapp.service.CompanyService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/team/save")
    public void saveTeam(@RequestBody SaveTeamRequest request){
        companyService.saveTeam(request);

    }

    @PostMapping("/team/worker/save")
    public void saveWorker(@RequestBody SaveWorkerRequest request){
        companyService.saveWorker(request);

    }


    @GetMapping("/team/get")
    public List<TeamResponse> getTeams(){
       return companyService.getTeams();
   }

    @GetMapping("/team/worker/get")
    public List<WorkerResponse> getWorkers(){
        return companyService.getWorkers();
    }




}
