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
  
  constructor(private employeeService: EmployeeService) { }

  ngOnInit() {
  }

  createOrUpdateEmployee(employee){
    this.employeeService.createEmployee(employee).subscribe(
        (res: ServiceResponse) => {
          alert("Success");
          this.employees = res.data;
        },
        err => {
          alert("Error in creating employee.");
        }
      );
    }
  addNewEmployee(){
    let f = false;
    this.employees.forEach(t => {
      if(t.id === 0)
        f = true;
    });
    if(f)
        alert("Save the draft version first.");
    else
      this.employees.push(new Employee());
  }
}
