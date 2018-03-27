package com.talentsconnect.awayboard.controller;

import com.talentsconnect.awayboard.dto.EmployeeDto;
import com.talentsconnect.awayboard.dto.ServiceResponse;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.entity.Team;
import com.talentsconnect.awayboard.repo.EmployeeRepo;
import com.talentsconnect.awayboard.repo.TeamRepo;
import com.talentsconnect.awayboard.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ritesh on 26/3/18.
 */
@RestController
public class TeamController {

    private TeamRepo teamRepo;
    private EmployeeService employeeService;

    @Autowired
    public TeamController(TeamRepo teamRepo, EmployeeService employeeService) {
        this.teamRepo = teamRepo;
        this.employeeService = employeeService;
    }

    @GetMapping("/teams")
    public ResponseEntity<ServiceResponse<List<Team> >> getAllTeams(){
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.findAll()));
    }

    @GetMapping("/team/{team-id}")
    public ResponseEntity<ServiceResponse<Team>> getMapping(@RequestParam("team-id") Long id){
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.findById(id)));
    }

    @PostMapping("/team")
    public ResponseEntity<ServiceResponse<Team>> postTeam(@RequestBody Team team){
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.save(team)));
    }

    @PostMapping("/team/{team-id}/employee/{employee-id}")
    public ResponseEntity<ServiceResponse<Team>> addEmployeeToTeam(@RequestParam("team-id") Long teamId,
                                  @RequestParam("employee-id") Long employeeId){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeService.postEmployeeToTeam(teamId, employeeId)));
    }

    @DeleteMapping("/team/{team-id}/employee/{employee-id}")
    public ResponseEntity<ServiceResponse<Team>> deleteEmployeeFromTeam(@RequestParam("team-id") Long teamId,
                                       @RequestParam("employee-id") Long employeeId){
        return ResponseEntity.ok().body(new ServiceResponse<>(employeeService.deleteEmployeeFromTeam(teamId, employeeId)));
    }



}
