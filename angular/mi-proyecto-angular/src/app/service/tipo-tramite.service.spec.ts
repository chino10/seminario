import { TestBed } from '@angular/core/testing';

import { TipoTramiteService } from './tipo-tramite.service';

describe('TipoTramiteService', () => {
  let service: TipoTramiteService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoTramiteService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
