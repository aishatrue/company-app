package com.group.companyapp.service;

import com.group.companyapp.domain.Attendance;
import com.group.companyapp.domain.Team;
import com.group.companyapp.domain.Worker;
import com.group.companyapp.dto.request.SaveAttendanceRequest;
import com.group.companyapp.dto.request.SaveTeamRequest;
import com.group.companyapp.dto.request.SaveWorkerRequest;
import com.group.companyapp.dto.response.TeamResponse;
import com.group.companyapp.dto.response.WorkerResponse;
import com.group.companyapp.repository.AttendanceRepository;
import com.group.companyapp.repository.TeamRepository;
import com.group.companyapp.repository.WorkerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final TeamRepository teamRepository;

    private final WorkerRepository workerRepository;

    private final AttendanceRepository attendanceRepository;

    public CompanyService(TeamRepository teamRepository,WorkerRepository workerRepository,AttendanceRepository attendanceRepository) {
        this.teamRepository = teamRepository;
        this.workerRepository = workerRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public void saveTeam(SaveTeamRequest request){
        teamRepository.save(new Team(request.getName(),request.getManager(),request.getMemberCount()));
    }

    public void saveWorker(SaveWorkerRequest request){
        workerRepository.save(new Worker(request.getName(), request.getTeamName(), request.getRole(), request.getBirthday(),request.getWorkStartDate()));
    }

    public List<TeamResponse> getTeams(){
        List<Team> teams = teamRepository.findAll();
        return teams.stream().
                map(team -> new TeamResponse(team.getName(),team.getManager(),team.getMemberCount()))
                .collect(Collectors.toList());

    }

    public List<WorkerResponse> getWorkers(){
        List<Worker> workers = workerRepository.findAll();
        return workers.stream().
                map(worker -> new WorkerResponse(worker.getName(),worker.getTeamName(),worker.getRole(),worker.getBirthday(),worker.getWorkStartDate()))
                .collect(Collectors.toList());

    }
    public void SaveAttendance(SaveAttendanceRequest request){
        attendanceRepository.save(new Attendance(request.getWorkerId(),request.getWorkStart(),null,request.isWorking()));


    }
}
