import { TestBed } from '@angular/core/testing';

import { FosterService } from './foster.service';

describe('FosterService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: FosterService = TestBed.get(FosterService);
    expect(service).toBeTruthy();
  });
});
