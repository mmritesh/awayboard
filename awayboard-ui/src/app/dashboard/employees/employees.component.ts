import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-employees',
  templateUrl: './employees.component.html',
  styleUrls: ['./employees.component.scss']
})
export class EmployeesComponent implements OnInit {

  @Input()
  private employees;
  
  constructor() { }

  ngOnInit() {
  }

}
