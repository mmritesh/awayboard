import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TeamService } from '../../services/team/team.service';
import { ServiceResponse, Employee } from '../../model/models';
import { EmployeeService } from '../../services/employee/employee.service';
import { AppConstants } from '../app.constants';
import { Select2OptionData } from 'ng2-select2';
import { HttpErrorResponse } from "@angular/common/http";
import { UtilService } from '../../services/util/util.service';

@Component({
  selector: 'app-awayboard',
  templateUrl: './awayboard.component.html',
  styleUrls: ['./awayboard.component.scss']
})
export class AwayboardComponent implements OnInit {
  id: number;
  private sub: any;
  private team: any;
  public employees;
  private allEmployees;
  private selection = -1;

  public show = false;
  private readonly statuses = AppConstants.STATUS;
  private employeeDropdownData = new Array<Select2OptionData>();



  constructor(private activatedRoute: ActivatedRoute, private teamService: TeamService,
    private appService: EmployeeService) {
  }

  ngOnInit() {
    this.allEmployees = this.activatedRoute.snapshot.data['employees'].data;
    this.initializeSelect2Data();
    this.sub = this.activatedRoute.params.subscribe(params => {
      this.id = +params['teamId']; // (+) converts string 'id' to a number
      this.getTeamById(this.id);
    });
  }

  ngOnDestroy() {
    this.sub.unsubscribe();
  }

  onOffice(e: any) {
    this.updateEmployeeStatus(e.dragData, AppConstants.STATUS[0]);
  }

  onHomeOffice(e: any) {
    this.updateEmployeeStatus(e.dragData, AppConstants.STATUS[1]);
  }

  onAway(e: any) {
    this.updateEmployeeStatus(e.dragData, AppConstants.STATUS[2]);
  }

  getTeamById(id) {
    this.show = false;
    this.teamService.getTeamById(id).subscribe(
      (res: ServiceResponse) => {
        this.team = res.data;
        this.employees = res.data.employees;
        this.show = true;
      },
      err => {
        this.show = false;
        alert("Error getting teams.");
      }
    );
  }

  addEmployeeToTeam(id) {
    let i = UtilService.getIndex(parseInt(id), this.team.employees);
    if (i == -1) {
      this.teamService.addEmployeeToTeam(id, this.team.id).subscribe(
        (res: ServiceResponse) => {
          this.getTeamById(this.team.id);
          this.selection = -1;
        },
        (err: HttpErrorResponse) => {
          alert(err.error.message);
        }
      );
    }else{
      alert("Employee already exist to the team.");
    }

  }

  removeEmployeeFromTeam(id) {
    this.teamService.removeEmployeeFromTeam(id, this.team.id).subscribe(
      (res: ServiceResponse) => {
        this.getTeamById(this.team.id);
      },
      (err: HttpErrorResponse) => {
        alert(err.error.message);
      }
    )
  }

  updateEmployeeStatus(e: Employee, status) {
    this.appService.updateEmployeeStatus(e.id, status).subscribe(
      (res: ServiceResponse) => {
        let i = UtilService.getIndex(res.data.id, this.employees);
        this.employees[i].currentStatus = status;
      },
      err => {
        alert("Error in updating employee status.");
      }
    );
  }

  initializeSelect2Data() {
    this.allEmployees.forEach(emp => {
      this.employeeDropdownData.push({
        id: emp.id,
        text: emp.name
      })
    });
  }

}
