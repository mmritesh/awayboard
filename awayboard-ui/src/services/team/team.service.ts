import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AppConstants } from '../../app/app.constants';


@Injectable()
export class TeamService {

  constructor(private http:HttpClient) { 

  }

  getAllTeams(){
    return this.http.get(AppConstants.API_BASE + AppConstants.API_GET_TEAMS);
  }

  createTeam(teamDto){
    return this.http.post(AppConstants.API_BASE + AppConstants.API_TEAM, teamDto);
  }
  
  updateTeam(teamDto){
    return this.http.put(AppConstants.API_BASE + AppConstants.API_TEAM, teamDto);
  }

  getTeamById(id){
      return this.http.get(AppConstants.API_BASE + AppConstants.API_TEAM + "/" + id);
  }

  deleteTeamById(id){
    return this.http.delete(AppConstants.API_BASE + AppConstants.API_TEAM + "/" + id);
  }

}
