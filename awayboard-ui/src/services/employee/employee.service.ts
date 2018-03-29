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

}
