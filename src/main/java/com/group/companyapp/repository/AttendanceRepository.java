package com.group.companyapp.repository;

import com.group.companyapp.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {

    Optional<Attendance> findByWorkerIdAndTodayDate(Long workerId,String todayDate);


boolean existsByWorkerIdAndTodayDate(Long workerId,String todayDate);


}
