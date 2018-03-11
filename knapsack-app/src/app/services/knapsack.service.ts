import { Injectable } from '@angular/core';
import { Http, Response } from "@angular/http";
import { Observable } from "rxjs/Observable";
import 'rxjs/add/operator/map';
import 'rxjs/add/operator/catch';
import { ITask } from "../model/task";
import { TaskTimes } from "../model/tasktimes";
import { RequestOptions } from '@angular/http/src/base_request_options';
import { ISolutionResponse } from '../model/solution-response';

@Injectable()
export class KnapsackService {

  private _tasksURL = "/knapsack/admin/tasks";
  private _solutionURL = "/knapsack/solutions";
 
  constructor(private http: Http) {
  }

  getTasks(): Observable<ITask[]> {
   
      return this.http
          .get(this._tasksURL)
          .map((response: Response) => {
              return <ITask[]>response.json();
          })
          .catch(this.handleError);
  }

  getSolution(id): Observable<ISolutionResponse> {
   
    return this.http
        .get(this._solutionURL+"/"+id)
        .map((response: Response) => {
            return <ISolutionResponse>response.json();
        })
        .catch(this.handleError);
}

  private handleError(error: Response) {
      return Observable.throw(error.statusText);
  }

}
