import { TestBed } from '@angular/core/testing';

import { DetalleTramiteService } from './detalle-tramite.service';

describe('DetalleTramiteService', () => {
  let service: DetalleTramiteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetalleTramiteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
