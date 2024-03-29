package com.group.companyapp.repository;

import com.group.companyapp.domain.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team,Long> {

    boolean existsByName(String teamName);
    Optional<Team> findByName(String teamName);

}
