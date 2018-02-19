import { NgModule } from '@angular/core';
import { TasksComponent } from './tasks/tasks.component';
import { RouterModule, Routes } from '@angular/router';

const routes: Routes = [
  { path: 'tasks', component: TasksComponent },
  { path: 'home', component: TasksComponent },
  { path: 'status', component: TasksComponent },
  { path: '', component: TasksComponent }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
