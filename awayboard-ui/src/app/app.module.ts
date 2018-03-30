import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { EmployeesComponent } from './dashboard/employees/employees.component';
import { TeamsComponent } from './dashboard/teams/teams.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { HttpClientModule } from '@angular/common/http';
import { EmployeeService } from '../services/employee/employee.service';
import { TeamService } from '../services/team/team.service';
import { AppRoutingModule } from './app-routing.module';
import { EmployeeResolver } from '../resolvers/employee.resolver';
import { TeamResolver } from '../resolvers/team.resolver';
import { AwayboardComponent } from './awayboard/awayboard.component';
import { TeamByIdResolver } from '../resolvers/team-by-id.resolver';
import { Ng2DragDropModule } from 'ng2-drag-drop';
import { Select2Module } from "ng2-select2/ng2-select2";

@NgModule({
  declarations: [
    AppComponent,
    EmployeesComponent,
    TeamsComponent,
    DashboardComponent,
    AwayboardComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,
    Ng2DragDropModule.forRoot(),
    Select2Module
  ],
  providers: [EmployeeService, TeamService, EmployeeResolver, TeamResolver, TeamByIdResolver],
  bootstrap: [AppComponent]
})
export class AppModule { }
