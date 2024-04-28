import { TestBed } from '@angular/core/testing';

import { ConsuladoService } from './consulado.service';

describe('ConsuladoService', () => {
  let service: ConsuladoService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ConsuladoService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
