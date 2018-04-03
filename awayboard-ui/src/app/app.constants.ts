import { Injectable } from '@angular/core';


@Injectable()
export class AppConstants{

    public static readonly TEAM_ID = "teamId";
    public static readonly STATUS = ['OFFICE', 'HOME_OFFICE', 'AWAY' ];
    public static readonly STATUS_TO_DISPLAY = {
        "OFFICE": "Office",
        "HOME_OFFICE": "Home Office",
        "AWAY": "Away"
    }
    public static readonly API_BASE = "";
    public static readonly API_GET_TEAMS = "/teams";
    public static readonly API_GET_EMPLOYEES = "/employees";
    public static readonly API_TEAM = "/team";
    public static readonly API_EMPLOYEE = "/employee";
    public static readonly API_STATUS = "/status";
}
