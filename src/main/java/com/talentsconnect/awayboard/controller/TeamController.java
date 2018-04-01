package com.talentsconnect.awayboard.controller;

import com.talentsconnect.awayboard.dto.ServiceResponse;
import com.talentsconnect.awayboard.entity.Employee;
import com.talentsconnect.awayboard.entity.Team;
import com.talentsconnect.awayboard.repo.TeamRepo;
import com.talentsconnect.awayboard.service.AppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by ritesh on 26/3/18.
 */
@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeamController {

    private TeamRepo teamRepo;
    private AppService appService;

    @Autowired
    public TeamController(TeamRepo teamRepo, AppService appService) {
        this.teamRepo = teamRepo;
        this.appService = appService;
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
    public ResponseEntity<ServiceResponse<Team>> updateTeamById(@RequestBody Team team){
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.save(team)));
    }

    @PostMapping("/team")
    public ResponseEntity<ServiceResponse<Team>> postTeam(@RequestBody Team team){
        return ResponseEntity.ok().body(new ServiceResponse<>(teamRepo.save(team)));
    }

    @DeleteMapping("/team/{team-id}")
    public ResponseEntity<ServiceResponse<Team>> deleteTeam(@PathVariable("team-id") Long id){
        appService.deleteTeam(id);
        return ResponseEntity.ok().body(new ServiceResponse<>(null));
    }

    @PutMapping("/team/{team-id}/employee/{employee-id}")
    public ResponseEntity<ServiceResponse<Team>> addEmployeeToTeam(@PathVariable("team-id") Long teamId,
                                                                   @PathVariable ("employee-id") Long employeeId){
        return ResponseEntity.ok().body(new ServiceResponse<>(appService.putEmployeeToTeam(teamId, employeeId)));
    }

    @DeleteMapping("/team/{team-id}/employee/{employee-id}")
    public ResponseEntity<ServiceResponse<Team>> removeEmployeeFromTeam(@PathVariable("team-id") Long teamId,
                                                                        @PathVariable("employee-id") Long employeeId){
        return ResponseEntity.ok().body(new ServiceResponse<>(appService.deleteEmployeeFromTeam(teamId, employeeId)));
    }



}
