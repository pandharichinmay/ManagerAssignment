import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginComponent } from "./login/login.component";
import { ListManagerComponent } from './manager/list-manager/list-manager.component';
import { ListEmployeeComponent } from './employee/list-employee/list-employee.component';
import { EditManagerComponent } from './manager/edit-manager/edit-manager.component';
import { EditEmployeeComponent } from './employee/edit-employee/edit-employee.component';
import { AddManagerComponent } from './manager/add-manager/add-manager.component';
import { AddEmployeeComponent } from './employee/add-employee/add-employee.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'add-employee', component: AddEmployeeComponent },
  { path: 'list-employee', component: ListEmployeeComponent },
  { path: 'edit-employee', component: EditEmployeeComponent },
  { path: 'add-manager', component: AddManagerComponent },
  { path: 'list-manager', component: ListManagerComponent },
  { path: 'edit-manager', component: EditManagerComponent },
  { path: '', component: LoginComponent }
];

export const routing = RouterModule.forRoot(routes);