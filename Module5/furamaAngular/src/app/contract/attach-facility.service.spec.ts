import { TestBed } from '@angular/core/testing';

import { AttachFacilityService } from './attach-facility.service';

describe('AttachFacilityService', () => {
  let service: AttachFacilityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AttachFacilityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
