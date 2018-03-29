import {Injectable} from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router'
import { TeamService } from '../services/team/team.service';

@Injectable()
export class TeamResolver implements Resolve<any> {
    constructor(private teamService:TeamService) {}

    resolve(route: ActivatedRouteSnapshot){
        return this.teamService.getAllTeams();;
    }
}
