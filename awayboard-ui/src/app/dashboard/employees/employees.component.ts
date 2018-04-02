import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../../services/employee/employee.service';
import { Employee, ServiceResponse } from '../../../model/models';
import { HttpErrorResponse } from '@angular/common/http';
import { UtilService } from '../../../services/util/util.service';
import { TeamService } from "../../../services/team/team.service";
import { AppConstants } from '../../app.constants';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {

  @Input()
  private employees;
  @Input()
  private teams;
  private selectedTeam = -1;

  private readonly statuses = AppConstants.STATUS;
  private readonly statusToRead = AppConstants.STATUS_TO_DISPLAY;

  constructor(private appService: EmployeeService, private teamService: TeamService) { }

  ngOnInit() {
  }

  createOrUpdateEmployee(employee) {
    if(!employee.editable)
      employee.editable = true;
    else{
      if(employee.id === 0){
        this.appService.createEmployee(employee).subscribe(
          (res: ServiceResponse) => {
            alert("Success");
            employee.id = res.data.id;
            employee.editable = false;
          },
          err => {
            alert("Error in creating employee.");
            console.log("Error ", err);
          }
        );
      }else {
        this.appService.updateEmployee(employee).subscribe(
          (res: ServiceResponse) => {
            alert("Success");
            // this.employees = res.data;
            employee.editable = false;
          },
          ( err: ServiceResponse) => {
            alert("Error in updating employee. ")
            console.log("Error, ", err);
          }
        );
      }
    }
  }
  addNewEmployee() {
    let f = false;
    this.employees.forEach(t => {
      if (t.id === 0)
        f = true;
    });
    if (f)
      alert("Save the draft version first.");
    else
      this.employees.push(new Employee());
  }

  deleteEmployee(id, i) {
    if (id === 0) {
       this.employees.splice(i, 1);
    } else {
      this.appService.deleteEmployee(id).subscribe(
        (res: ServiceResponse) => {
          this.employees.splice(i, 1);
          alert("Success.")
        },
        err => {
          alert("Error in deleting employee")
        }
      );
    }
  }

  removeTeamFromEmployee(empId, i, teamId, j){
    this.appService.removeTeamFromEmployee(empId, teamId).subscribe(
      (res:ServiceResponse) => {
        this.employees[i].teams.splice(j, 1);
        alert("Removed team from employee.");        
      },
      (err: HttpErrorResponse) => {
        alert(err.error.message);
      }
    )
  }

  addTeamToEmployee(teamIndex, employee){
    //employee.teams.push(team);
    //this.createOrUpdateEmployee(employee);
    console.log("Hii: ", teamIndex, employee);
    let i = UtilService.getIndex(this.teams[teamIndex].id, employee.teams);
    if(i == -1){
      this.teamService.addEmployeeToTeam(employee.id, this.teams[teamIndex].id).subscribe(
      (res:ServiceResponse) => {
        alert("Employee added to team.")
        window.location.reload(true);
      },
      (err: HttpErrorResponse) => {
        alert(err.error.message);
      }
    );
    }else{
      alert("Employee is already in the selected team.");
      this.selectedTeam = -1;
    }

    
  }

  onChange(event){
    this.selectedTeam = -1;
  }
  // getAllEmployees(){
  //   this.appService.getAllEmployees().subscribe(
  //     (res: ServiceResponse) => {
  //       this.employees = res.data;
  //     },
  //     err => {
  //       alert("Error in getting employees.");
  //     }
  //   )
  // }
}
