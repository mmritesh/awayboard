package com.talentsconnect.awayboard.service.impl;

import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.entity.Team;
import com.talentsconnect.awayboard.repo.EmployeeRepo;
import com.talentsconnect.awayboard.repo.TeamRepo;
import com.talentsconnect.awayboard.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

/**
 * Created by ritesh on 27/3/18.
 */

@Service
@Transactional
public class AppServiceImpl implements AppService {

    private EmployeeRepo employeeRepo;
    private TeamRepo teamRepo;

    @Autowired
    public AppServiceImpl(EmployeeRepo employeeRepo, TeamRepo teamRepo) {
        this.employeeRepo = employeeRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public Team putEmployeeToTeam(Long teamId, Long employeeId) {
        Team team = teamRepo.findById(teamId);
        team.getEmployees().add(employeeRepo.findById(employeeId));
        return teamRepo.save(team);
    }

    @Override
    public Team deleteEmployeeFromTeam(Long teamId, Long employeeId) {

        Team team = teamRepo.findById(teamId);
        team.getEmployees().removeIf(emp -> Objects.equals(emp.getId(), employeeId));
        return teamRepo.save(team);
    }

    @Override
    public Employee updateEmployeeStatus(Long employeeId, Employee.Status status){
        Employee employee = employeeRepo.findById(employeeId);
        employee.setCurrentStatus(status);
        return employeeRepo.save(employee);
    }

    @Transactional
    @Override
    public void deleteEmployee(Long id){
        employeeRepo.deleteEmployeeTeamRelation(id);
        employeeRepo.delete(id);
    }

    @Transactional
    @Override
    public void deleteTeam(Long id){
        teamRepo.deleteEmployeeTeamRelation(id);
        teamRepo.delete(id);
    }

    @Transactional
    @Override
    public int removeTeamFromEmployee(Long empId, Long teamId){
        return teamRepo.removeTeamFromEmployee(empId, teamId);
    }
}
