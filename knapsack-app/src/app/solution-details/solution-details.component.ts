import { Component, OnInit } from '@angular/core';
import { KnapsackService } from '../services/knapsack.service'
import { ISolutionResponse } from "../model/solution-response";
import { Router, ActivatedRoute, ParamMap } from '@angular/router';
import 'rxjs/add/operator/switchMap';
import { Observable } from 'rxjs/Observable';

@Component({
  selector: 'app-solution-details',
  templateUrl: './solution-details.component.html',
  styleUrls: ['./solution-details.component.css'],
  providers: [KnapsackService]
})
export class SolutionDetailsComponent implements OnInit {
  solution$: Observable<ISolutionResponse>;
  isolution: ISolutionResponse;
  constructor( private route: ActivatedRoute,
    private router: Router, private service: KnapsackService) { }


    getSolution(id): void {
      this.service.getSolution(id)
          .subscribe(
              resultArray => this.isolution = resultArray,
              error => console.log("Error :: " + error)
          )
  }

  ngOnInit() {
    let id = this.route.snapshot.paramMap.get('id');
    this.getSolution(id);
    // let id = this.route.snapshot.paramMap.get('id');
    // this.solution$ = this.service.getSolution(id);
    // this.solution$.subscribe(res => {
    //   console.log(res);
    //   this.isolution = res;
    // });
  }
}
