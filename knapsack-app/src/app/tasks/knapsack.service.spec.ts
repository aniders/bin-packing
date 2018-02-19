import { TestBed, inject } from '@angular/core/testing';

import { KnapsackService } from './knapsack.service';

describe('KnapsackService', () => {
  beforeEach(() => {
    TestBed.configureTestingModule({
      providers: [KnapsackService]
    });
  });

  it('should be created', inject([KnapsackService], (service: KnapsackService) => {
    expect(service).toBeTruthy();
  }));
});
