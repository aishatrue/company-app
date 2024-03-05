package com.group.companyapp.controller;

import com.group.companyapp.dto.request.*;
import com.group.companyapp.dto.response.AttendanceResponse;
import com.group.companyapp.dto.response.FinalAttendanceResponse;
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

    @GetMapping("team/worker/getworktime")
    public FinalAttendanceResponse getWorkTime(@RequestBody GetWorkTimeRequest request){
        return companyService.getWorkTime(request);

    }
    @PostMapping("team/worker/dayoff")
    public void SaveWorkerDayoff(@RequestBody SaveWorkerDayOffRequest request){
        companyService.SaveWorkerDayOff(request);

    }

    @GetMapping("team/worker/getdayoff")
    public Long GetDayOff(@RequestBody GetDayOffRequest request){
        return companyService.GetGetoffTime(request);

    }
  



}
