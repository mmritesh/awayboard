import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TeamService } from '../../services/team/team.service';
import { ServiceResponse } from '../../model/models';

@Component({
  selector: 'app-awayboard',
  templateUrl: './awayboard.component.html',
  styleUrls: ['./awayboard.component.scss']
})
export class AwayboardComponent implements OnInit {
  id: number;
  private sub: any;
  private team: any;

  constructor(private activatedRoute: ActivatedRoute, private teamService: TeamService) {}

  ngOnInit() {
    // this.team = this.activatedRoute.snapshot.data['team'].data;
    this.sub = this.activatedRoute.params.subscribe(params => {
      this.id = +params['teamId']; // (+) converts string 'id' to a number
      this.getTeamById(this.id);
    });
  }

  ngOnDestroy() {
  }

  getTeamById(id){
    this.teamService.getTeamById(id).subscribe(
      (res: ServiceResponse) => {
        this.team = res.data;
        console.log("Awayboard Component: ", this.team);        
      },
      err => {
        alert("Error getting teams.");
      }
    );        
  }

}
