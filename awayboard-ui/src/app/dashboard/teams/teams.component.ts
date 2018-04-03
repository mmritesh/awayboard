import { Component, OnInit, Input } from '@angular/core';
import { Team, ServiceResponse } from '../../../model/models';
import { TeamService } from '../../../services/team/team.service';
import { AppConstants } from '../../app.constants';
import { Router } from '@angular/router';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent implements OnInit {

  @Input()
  public teams;

  constructor(private teamService: TeamService, private router: Router) { }

  ngOnInit() {
  }

  addNewTeam(){
    let f = false;
    this.teams.forEach(t => {
      if(t.id === 0)
        f = true;
    });
    if(f)
        alert("Save the draft version first.");
    else
      this.teams.unshift(new Team());
  }

  saveOrUpdate(team){
    if(!team.editable)
      team.editable = true;
    else{
      if(team.id == 0){
        this.teamService.createTeam(team).subscribe(
          (res: ServiceResponse) => {
            team.editable = false;
            team.id = res.data.id;
            alert("Success");
            console.log("Team save successfully");
          },
          err => {
            alert("Error in saving team.");
            console.log("Error in saving team.");
          }
        );
      }else{
        this.teamService.updateTeam(team).subscribe(
          data => {
            team.editable = false;
            alert("Success");
            console.log("Team updated successfully");
          },
          err => {
            alert("Error in updating team.");
            console.log("Error in updating team.");
          }
        );
      }
    }
  }
  deleteTeam(id, i){
    if(id === 0){
      this.teams.splice(i, 1);
    }else{
      this.teamService.deleteTeamById(id).subscribe(
      (res: ServiceResponse) => {
        alert("Success")
        console.log("delete: ", res);
        window.location.reload(true);
      },
      err => {
        alert("Error in deleting team.");
      }
    );
    }
    
  }

  viewAwayboard(teamId){
    sessionStorage.setItem(AppConstants.TEAM_ID, teamId);
    this.router.navigate(['/awayboard', teamId]);
  }
}
