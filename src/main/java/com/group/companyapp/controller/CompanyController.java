package com.group.companyapp.controller;

import com.group.companyapp.dto.request.SaveAttendanceRequest;
import com.group.companyapp.dto.request.SaveTeamRequest;
import com.group.companyapp.dto.request.SaveUpdateGetOffRequest;
import com.group.companyapp.dto.request.SaveWorkerRequest;
import com.group.companyapp.dto.response.TeamResponse;
import com.group.companyapp.dto.response.WorkerResponse;
import com.group.companyapp.service.CompanyService;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("team/worker/goto")
    public  void SaveAttendance(@RequestBody SaveAttendanceRequest request){
        companyService.SaveAttendance(request);
    }

    @PutMapping("team/worker/getoff")
    public  void UpdateGetOff(@RequestBody SaveUpdateGetOffRequest request){
        companyService.UpdateGetOff(request);
    }
  



}
