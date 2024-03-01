package com.group.companyapp.service;

import com.group.companyapp.domain.Team;
import com.group.companyapp.dto.request.saveTeamRequest;
import com.group.companyapp.repository.TeamRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final TeamRepository teamRepository;

    public CompanyService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public void saveTeam(saveTeamRequest request){
        teamRepository.save(new Team(request.getName(),request.getManager(),request.getMemberCount()));
    }
}
