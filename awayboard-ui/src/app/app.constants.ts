import { Injectable } from '@angular/core';


@Injectable()
export class AppConstants{
    public static API_BASE = "http://localhost:8080";
    public static API_GET_TEAMS = "/teams";
    public static API_GET_EMPLOYEES = "/employees";
    public static API_TEAM = "/team";
    public static API_EMPLOYEE = "/employee";
}