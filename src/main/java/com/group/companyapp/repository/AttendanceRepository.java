package com.group.companyapp.repository;

import com.group.companyapp.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance,Long> {



}
