import { TestBed } from '@angular/core/testing';

import { GpaService } from './gpa.service';

describe('GpaService', () => {
  let service: GpaService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GpaService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
