import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { EmployeeResolver } from '../resolvers/employee.resolver';
import { TeamResolver } from '../resolvers/team.resolver';

const routes: Routes = [
  {path: '', redirectTo: '/dashboard', pathMatch: 'full'},
  {path: 'dashboard', component: DashboardComponent, resolve: {employees : EmployeeResolver, teams : TeamResolver} },

];

@NgModule({
  imports: [RouterModule.forRoot(routes, { useHash: true })],
  exports: [RouterModule]
})

export class AppRoutingModule {
}
