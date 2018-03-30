package com.talentsconnect.awayboard.service.impl;

import com.talentsconnect.awayboard.dto.EmployeeDto;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.entity.Team;
import com.talentsconnect.awayboard.exception.CustomException;
import com.talentsconnect.awayboard.exception.ErrorCode;
import com.talentsconnect.awayboard.repo.EmployeeRepo;
import com.talentsconnect.awayboard.repo.TeamRepo;
import com.talentsconnect.awayboard.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by ritesh on 27/3/18.
 */

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService{

    private EmployeeRepo employeeRepo;
    private TeamRepo teamRepo;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepo employeeRepo, TeamRepo teamRepo) {
        this.employeeRepo = employeeRepo;
        this.teamRepo = teamRepo;
    }

    @Override
    public Employee saveEmployee(EmployeeDto employeeDto) {
        if (employeeDto.getId() == 0){
            Employee employee = new Employee();
            mapEmployeeDetails(employeeDto, employee);
            employee.setId(0l);
            return employeeRepo.save(employee);
        }else {
            throw new CustomException(ErrorCode.EMPLOYEE_ALREADY_EXIT);
        }

    }

    @Override
    public Team postEmployeeToTeam(Long teamId, Long employeeId) {

        Employee employee = employeeRepo.findById(employeeId);

        if (employee.getTeams().stream().noneMatch(t -> Objects.equals(t.getId(), teamId))) {
            Team team = teamRepo.getOne(teamId);
            employee.getTeams().add(team);
            employeeRepo.save(employee);
            return teamRepo.findById(teamId);
        }
        else {
            throw new CustomException(ErrorCode.EMPLOYEE_EXIST_TO_TEAM);
        }
    }

    @Override
    public Team deleteEmployeeFromTeam(Long teamId, Long employeeId) {

        Employee employee = employeeRepo.getOne(employeeId);
        employee.getTeams().removeIf(t -> Objects.equals(t.getId(), teamId));
        employeeRepo.save(employee);
        return teamRepo.findById(teamId);
    }

    @Override
    public Employee updateEmployee(EmployeeDto employeeDto){
        if (employeeDto.getId() != 0){
            Employee employee = employeeRepo.findById(employeeDto.getId());
            mapEmployeeDetails(employeeDto, employee);
            return employeeRepo.save(employee);
        }else {
            throw new CustomException(ErrorCode.INVALID_EMPLOYEE_ID);
        }
    }

    @Override
    public Employee updateEmployeeStatus(Long employeeId, Employee.Status status){
        Employee employee = employeeRepo.findById(employeeId);
        employee.setCurrentStatus(status);
        return employeeRepo.save(employee);
    }

    private Employee mapEmployeeDetails(EmployeeDto employeeDto, Employee employee){
        employee.setImageUrl(employeeDto.getImageUrl());
        employee.setCurrentStatus(employeeDto.getCurrentStatus());
        employee.setName(employeeDto.getName());

        if (employee.getTeams() != null && !employee.getTeams().isEmpty()){
            List<Team> teams = new ArrayList<>();

            try {
                employeeDto.getTeams().forEach(teamId -> teams.add(teamRepo.findById(teamId)));
            }catch (EntityNotFoundException e){
                throw new CustomException(ErrorCode.UNABLE_FIND_ONE_OF_THE_TEAMS);
            }
            employee.setTeams(teams);
        }

        return employee;
    }

}
