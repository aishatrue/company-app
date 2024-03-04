package com.group.companyapp.service;

import com.group.companyapp.domain.Attendance;
import com.group.companyapp.domain.Team;
import com.group.companyapp.domain.Worker;
import com.group.companyapp.dto.request.*;
import com.group.companyapp.dto.response.AttendanceResponse;
import com.group.companyapp.dto.response.FinalAttendanceResponse;
import com.group.companyapp.dto.response.TeamResponse;
import com.group.companyapp.dto.response.WorkerResponse;
import com.group.companyapp.repository.AttendanceRepository;
import com.group.companyapp.repository.TeamRepository;
import com.group.companyapp.repository.WorkerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final TeamRepository teamRepository;

    private final WorkerRepository workerRepository;

    private final AttendanceRepository attendanceRepository;

    private final JdbcTemplate jdbcTemplate;

    public CompanyService(TeamRepository teamRepository,WorkerRepository workerRepository,AttendanceRepository attendanceRepository,JdbcTemplate jdbcTemplate) {
        this.teamRepository = teamRepository;
        this.workerRepository = workerRepository;
        this.attendanceRepository = attendanceRepository;
        this.jdbcTemplate = jdbcTemplate;
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
        attendanceRepository.save(new Attendance(request.getWorkerId(), request.getTodayDate(), request.getWorkStart(),null,request.isWorkState()));


    }
    public void UpdateGetOff(SaveUpdateGetOffRequest request){


        Attendance attendance = attendanceRepository.findByWorkerIdAndTodayDate(request.getWorkerId(), request.getTodayDate())
                .orElseThrow(IllegalArgumentException::new);

        attendance.updateAttendance(request.getWorkEnd(),request.isWorkState());
        attendanceRepository.save(attendance);
    }

    public FinalAttendanceResponse getWorkTime(GetWorkTimeRequest request){
        String sql = "SELECT SUM(TIMESTAMPDIFF(MINUTE,work_end,work_start))as working_minutes,today_date FROM attendance  WHERE DATE_FORMAT(today_date, '%Y-%m') = ? AND worker_id = ? GROUP BY today_date";
        List<AttendanceResponse> attendanceResponseList=  jdbcTemplate.query(sql, (rs, rowNum) -> {
            Date todayDate = rs.getDate("today_date");
            long workingMinutes = rs.getLong("working_minutes");
            return new AttendanceResponse(todayDate,workingMinutes);
        },request.getYearMonth(),request.getWorkerId());

        long sum=0;

        for (AttendanceResponse attendanceResponse : attendanceResponseList) {

            sum= sum+ attendanceResponse.getWorkingMinutes();

        }

        return new FinalAttendanceResponse(sum,attendanceResponseList);





    }
}
