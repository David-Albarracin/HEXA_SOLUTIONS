import { TestBed } from '@angular/core/testing';

import { EasyCrudService } from './easy-crud.service';

describe('EasyCrudService', () => {
  let service: EasyCrudService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EasyCrudService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
