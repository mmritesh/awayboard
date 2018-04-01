import { Component, OnInit, Input } from '@angular/core';
import { EmployeeService } from '../../../services/employee/employee.service';
import { Employee, ServiceResponse } from '../../../model/models';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {

  @Input()
  private employees;

  constructor(private appService: EmployeeService) { }

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
        )
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
}
