import { Component, OnInit, Input } from '@angular/core';
import { Team } from '../../../model/employee';

@Component({
  selector: 'app-teams',
  templateUrl: './teams.component.html',
  styleUrls: ['./teams.component.scss']
})
export class TeamsComponent implements OnInit {

  @Input()
  private teams;


  constructor() { }

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
      this.teams.push(new Team());
    // document.getElementById("team_0").classList.add("show");
  }

  deleteFromTeam(i){
    this.teams.splice(i, 1);
  }

}
