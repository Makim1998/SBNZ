import { TestBed } from '@angular/core/testing';

import { RequestSequelService } from './request-sequel.service';

describe('RequestSequelService', () => {
  let service: RequestSequelService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RequestSequelService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
