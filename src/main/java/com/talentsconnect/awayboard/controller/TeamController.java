package com.talentsconnect.awayboard.controller;

import com.talentsconnect.awayboard.dto.EmployeeDto;
import com.talentsconnect.awayboard.dto.ServiceResponse;
import com.talentsconnect.awayboard.dto.TeamDto;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.entity.Team;
import com.talentsconnect.awayboard.repo.EmployeeRepo;
import com.talentsconnect.awayboard.repo.TeamRepo;
import com.talentsconnect.awayboard.service.EmployeeService;
import com.talentsconnect.awayboard.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController {

    private TeamRepo teamRepo;
    private EmployeeService employeeService;
    private TeamService teamService;

    @Autowired
    public TeamController(TeamRepo teamRepo, EmployeeService employeeService, TeamService teamService) {
        this.teamRepo = teamRepo;
        this.employeeService = employeeService;
        this.teamService = teamService;
    }

    @GetMapping("/teams")
    public ResponseEntity<ServiceResponse<List<Team> >> getAllTeams(){
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.findAll()));
    }

    @GetMapping("/team/{team-id}")
    public ResponseEntity<ServiceResponse<Team>> getTeamById(@PathVariable("team-id") Long id){
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.findById(id)));
    }

    @PutMapping("/team")
    public ResponseEntity<ServiceResponse<List<Team>>> updateTeamById(@RequestBody Team team){
        teamRepo.save(team);
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.findAll()));
    }

    @DeleteMapping("/team/{team-id}")
    public ResponseEntity<ServiceResponse<List<Team>>> deleteTeam(@PathVariable("team-id") Long id){
        teamRepo.delete(id);
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.findAll()));
    }
    @PostMapping("/team")
    public ResponseEntity<ServiceResponse<List<Team>>> postTeam(@RequestBody Team team){
        teamRepo.save(team);
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.findAll()));
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
