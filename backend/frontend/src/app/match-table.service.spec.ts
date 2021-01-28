import { TestBed } from '@angular/core/testing';

import { MatchTableService } from './match-table.service';

describe('MatchTableService', () => {
  let service: MatchTableService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MatchTableService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
