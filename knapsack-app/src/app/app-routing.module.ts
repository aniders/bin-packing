import { NgModule } from '@angular/core';
import { TasksComponent } from './tasks/tasks.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'app-tasks', component: TasksComponent }
];

@NgModule({
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
