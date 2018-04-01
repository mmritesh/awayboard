import {Injectable} from '@angular/core';
import { Resolve, ActivatedRouteSnapshot } from '@angular/router'
import { EmployeeService } from '../services/employee/employee.service';
import { Observable } from "rxjs/Observable";
import { ServiceResponse } from '../model/models';

@Injectable()
export class EmployeeResolver implements Resolve<any> {
    constructor(private appService : EmployeeService) {}

    resolve(route: ActivatedRouteSnapshot ){
        console.log("Employee Resolver");
        return this.appService.getAllEmployees();
    }
}
