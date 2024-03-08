package com.group.companyapp.repository;

import com.group.companyapp.domain.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface WorkerRepository extends JpaRepository<Worker,Long> {

    Worker  findByTeamNameAndRole(String teamName,String role);
    boolean existsByTeamNameAndRole(String teamName,String role);
}
