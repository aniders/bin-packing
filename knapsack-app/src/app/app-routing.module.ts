import { NgModule } from '@angular/core';
import { TasksComponent } from './tasks/tasks.component';
import { RouterModule, Routes } from '@angular/router';
import { SolutionDetailsComponent } from './solution-details/solution-details.component'

const routes: Routes = [
  { path: 'tasks', component: TasksComponent },
  { path: 'home', component: TasksComponent },
  { path: 'status', component: TasksComponent },
  { path: '', component: TasksComponent },
  { path: 'solution/:id', component: SolutionDetailsComponent },

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [ RouterModule ]
})

export class AppRoutingModule { }
