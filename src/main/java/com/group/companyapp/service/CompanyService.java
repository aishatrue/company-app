package com.group.companyapp.service;

import com.group.companyapp.domain.Attendance;
import com.group.companyapp.domain.Team;
import com.group.companyapp.domain.Worker;
import com.group.companyapp.dto.request.*;
import com.group.companyapp.dto.response.*;
import com.group.companyapp.repository.AttendanceRepository;
import com.group.companyapp.repository.TeamRepository;
import com.group.companyapp.repository.WorkerRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private final TeamRepository teamRepository;

    private final WorkerRepository workerRepository;

    private final AttendanceRepository attendanceRepository;

    private JdbcTemplate jdbcTemplate;

    public CompanyService(TeamRepository teamRepository,WorkerRepository workerRepository,AttendanceRepository attendanceRepository,JdbcTemplate jdbcTemplate) {
        this.teamRepository = teamRepository;
        this.workerRepository = workerRepository;
        this.attendanceRepository = attendanceRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveTeam(SaveTeamRequest request){
        boolean teamExist = teamRepository.existsByName(request.getName());
        if(teamExist){
            System.out.println("동일한 팀이 등록되어있습니다.");
            throw new IllegalArgumentException();
        }

        teamRepository.save(new Team(request.getName(),request.getManager(),request.getMemberCount(),request.getDayOffOption()));
    }

    public void saveWorker(SaveWorkerRequest request){

        workerRepository.save(new Worker(request.getName(), request.getTeamName(), request.getRole(), request.getBirthday(),request.getWorkStartDate(), request.getDayOff()));
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

        //존재하지 않는 worker일 경우.
       Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow(IllegalArgumentException::new);

       //이미 출근을 한 직원이 또 출근하려 할 경우 & 이미 퇴근을 한 직원이 또 출근하려 할 경우..음 안되네
       boolean checkAttendance = attendanceRepository.existsByWorkerIdAndTodayDate(request.getWorkerId(), request.getTodayDate());


        if(checkAttendance){
            Optional<Attendance> attendance= attendanceRepository.findByWorkerIdAndTodayDate(request.getWorkerId(), request.getTodayDate());
            if(attendance.get().isWorkState()){
                System.out.println("이미 출근을 하셨는데,,잠이 덜 깨셨나요?");
                throw new IllegalArgumentException();

            }else{
                System.out.println("이미 퇴근을 하셨는데,,또 하시려구요?");
                throw new IllegalArgumentException();
            }


        }

        attendanceRepository.save(new Attendance(request.getWorkerId(), request.getTodayDate(), request.getWorkStart(),null,request.isWorkState(),false));


    }
    public void UpdateGetOff(SaveUpdateGetOffRequest request){


        //퇴근하려는 직원이 출근하지 않았던 경우
        Attendance attendance = attendanceRepository.findByWorkerIdAndTodayDate(request.getWorkerId(), request.getTodayDate())
                .orElseThrow(IllegalArgumentException::new);

        attendance.updateAttendance(request.getWorkEnd(),request.isWorkState());
        attendanceRepository.save(attendance);
    }

    public FinalAttendanceResponse getWorkTime(GetWorkTimeRequest request){
        String sql = "SELECT SUM(TIMESTAMPDIFF(MINUTE,work_start,work_end))as working_minutes,today_date,using_day_off FROM attendance  WHERE DATE_FORMAT(today_date, '%Y-%m') = ? AND worker_id = ? GROUP BY today_date,using_day_off";
        List<AttendanceResponse> attendanceResponseList =  jdbcTemplate.query(sql, (rs, rowNum) -> {
            Date todayDate = rs.getDate("today_date");
            long workingMinutes = rs.getLong("working_minutes");
            boolean usingDayOff = rs.getBoolean("using_day_off");
            return new AttendanceResponse(todayDate,workingMinutes,usingDayOff);
        },request.getYearMonth(),request.getWorkerId());

        //리스트에 값을 못 가져올 경우
        if(attendanceResponseList == null || attendanceResponseList.isEmpty()){
            System.out.println("데이터가 존재하지 않습니다.");
            throw new IllegalArgumentException();

        }

        long sum=0;

        for (AttendanceResponse attendanceResponse : attendanceResponseList) {

            sum= sum+ attendanceResponse.getWorkingMinutes();

        }

        return new FinalAttendanceResponse(sum,attendanceResponseList);
    }


    public void SaveWorkerDayOff(SaveWorkerDayOffRequest request){
        Date todayDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dayOffDate;


        try {
            //받아오는 dayoff는 string이라서 date끼리 연산을 위해 parse를 해줌.
            dayOffDate = new SimpleDateFormat("yyyy-MM-dd").parse(request.getDayOffDate());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        //받아온 workerId로 데이터를 조회. 없으면 익셉션 반환₩
        Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow(IllegalArgumentException::new);

        //해당 worker의 남은 연차가 0인지 아닌지 확인하거,0이면 남은연차가 없다고 출력
        //연차가 있으면 아래 로직 수행
        if(worker.getDayOff()!=0){
            //worker데이터에서 소속팀을 가져오고, 그걸로 team테이블에서 데이터를 가져옴
            Team team = teamRepository.findByName(worker.getTeamName())
                    .orElseThrow(IllegalArgumentException::new);

            //연차 사용일과 연차를 신청하는 오늘사이의 날짜를 빼서, 일자수 차이로 변환
            long diffSec = (dayOffDate.getTime() - todayDate.getTime()); //초 차이
            long diffDays = diffSec / (24*60*60); //일자수 차이

            //만약 그 일자 수 차이가 받아온 team데이터의 연차 등록 조건날짜보다 크거나 같으면 아래로직 수행
            //아니라면 최소 몇일 전에 등록해야 하는지 출력
            if(diffDays >= team.getDayOffOption()){
                System.out.println("연차 등록가능합니다.");
                String formattedToday = new SimpleDateFormat("yyyy-MM-dd").format(todayDate);
                attendanceRepository.save(new Attendance(request.getWorkerId(),formattedToday,formattedToday,formattedToday,false,true));

                worker.updateGetoff();
                workerRepository.save(worker);

            }else{
                System.out.println("최소 "+ team.getDayOffOption()+"일 전에 등록해주세요.");
            }

        }else{
            System.out.println("남은 연차가 없습니다.");
        }

    }

    public Long GetGetoffTime(GetDayOffRequest request){

       Worker worker = workerRepository.findById(request.getWorkerId())
                .orElseThrow(IllegalArgumentException::new);
       return worker.getDayOff();
    }

    public List<FinalAllworkerTimeResponse> GetOverTime(GetOverTimeRequest request) {
        String sql = "SELECT SUM(TIMESTAMPDIFF(MINUTE,work_end,work_start))as working_minutes,worker_id FROM attendance  WHERE DATE_FORMAT(today_date, '%Y-%m') = ? GROUP BY worker_id";
        List<AllWorkerTimeResponse> allWorkerTimeResponses = jdbcTemplate.query(sql, (rs, rowNum) -> {
            long workingMinutes = rs.getLong("working_minutes");
            long workerId = rs.getLong("worker_id");
            return new AllWorkerTimeResponse(workerId, workingMinutes);
        }, request.getYearMonth());

        List<FinalAllworkerTimeResponse> finalAllworkerTimeResponses = new ArrayList<>();
        for (AllWorkerTimeResponse allWorkerTimeResponse : allWorkerTimeResponses) {
            Long overTime = (allWorkerTimeResponse.getWorkingMinutes()/60)-160;
            Optional<Worker> worker = workerRepository.findById(allWorkerTimeResponse.getWorkerId());

            finalAllworkerTimeResponses.add(new FinalAllworkerTimeResponse(allWorkerTimeResponse.getWorkerId(),worker.get().getName(),overTime));


        }
        return finalAllworkerTimeResponses;


    }

}
