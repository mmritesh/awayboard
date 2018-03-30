import {Injectable} from '@angular/core';
import { Resolve, ActivatedRouteSnapshot, ActivatedRoute } from '@angular/router';
import { TeamService } from '../services/team/team.service';
import { AppConstants } from '../app/app.constants';

@Injectable()
export class TeamByIdResolver implements Resolve<any> {
    id: number;
    private sub: any;

    constructor(private activatedRoute: ActivatedRoute, private teamService:TeamService) {}

    ngOnInit(){
        this.sub = this.activatedRoute.params.subscribe(params => {
            this.id = +params['teamId']; // (+) converts string 'id' to a number
            
        });
    }
    ngOnDestroy() {
        this.sub.unsubscribe();
    }

    resolve(route: ActivatedRouteSnapshot){
            console.log("Team by Id resolved.", this.activatedRoute.snapshot.queryParams["teamId"])
        
        return this.teamService.getTeamById(this.activatedRoute.snapshot.queryParams["teamId"]);        
    }
}
