package com.talentsconnect.awayboard.service.impl;

import com.talentsconnect.awayboard.dto.TeamDto;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.entity.Team;
import com.talentsconnect.awayboard.exception.CustomException;
import com.talentsconnect.awayboard.exception.ErrorCode;
import com.talentsconnect.awayboard.repo.EmployeeRepo;
import com.talentsconnect.awayboard.repo.TeamRepo;
import com.talentsconnect.awayboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ritesh on 29/3/18.
 */

@Service
public class TeamServiceImpl implements TeamService{

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private  TeamRepo teamRepo;

    @Override
    public Team updateTeam(TeamDto team) {
        if (team.getId() != 0) {
            Team t = teamRepo.findById(team.getId());
            mapTeamDtoToTeam(team, t);
            return teamRepo.save(t);
        }else
            throw new CustomException(ErrorCode.INVALID_TEAM_ID);
    }

    @Override
    public Team saveTeam(TeamDto teamDto){
        if (teamDto.getId() == 0){
            Team team = new Team();
            mapTeamDtoToTeam(teamDto, team);
            return team;
        }else
            throw new CustomException(ErrorCode.TEAM_ALREADY_EXIST);
    }
    private void mapTeamDtoToTeam(TeamDto teamDto, Team t){
        t.setImageUrl(teamDto.getImageUrl());
        t.setName(teamDto.getName());

        List<Employee> emp = new ArrayList<>();

        for (Long id : teamDto.getEmployees()) {
            emp.add(employeeRepo.findById(id));
        }
        if (!emp.isEmpty())
            t.setEmployees(emp);

    }

}
