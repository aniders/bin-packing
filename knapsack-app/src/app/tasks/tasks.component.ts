import { Component, OnInit } from '@angular/core';
import { KnapsackService } from '../services/knapsack.service'
import { ITask } from "../model/task";
@Component({
  selector: 'app-tasks',
  templateUrl: './tasks.component.html',
  styleUrls: ['./tasks.component.css'], 
  providers: [KnapsackService]
})
export class TasksComponent implements OnInit {
  _tasksArray: ITask[];
  constructor(private knapsackService: KnapsackService) { }

  getTasks(): void {
    this.knapsackService.getTasks()
        .subscribe(
            resultArray => this._tasksArray = resultArray,
            error => console.log("Error :: " + error)
        )
}

  ngOnInit() {
    this.getTasks();
  }

}
