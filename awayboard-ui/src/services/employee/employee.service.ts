import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { AppConstants } from '../../app/app.constants';
import { AppComponent } from '../../app/app.component';

@Injectable()
export class EmployeeService {

  constructor(private http:HttpClient) { }

  getAllEmployees(){
    return this.http.get(AppConstants.API_BASE + AppConstants.API_GET_EMPLOYEES);
  }

  createEmployee(employee){
    return this.http.post(AppConstants.API_BASE + AppConstants.API_EMPLOYEE, employee);
  }

  deleteEmployee(id){
    return this.http.delete(AppConstants.API_BASE + AppConstants.API_EMPLOYEE + "/" + id);
  }

  updateEmployee(employee){
    return this.http.put(AppConstants.API_BASE + AppConstants.API_EMPLOYEE, employee);
  }

  updateEmployeeStatus(id, status){
    return this.http.put(AppConstants.API_BASE + AppConstants.API_EMPLOYEE + "/" + id + AppConstants.API_STATUS + "/" + status, null);
  }

  removeTeamFromEmployee(empId, teamId){
    return this.http.delete(AppConstants.API_BASE + AppConstants.API_EMPLOYEE + "/" + empId + 
        AppConstants.API_TEAM + "/" + teamId);
  }
}
