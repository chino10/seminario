import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TipoTramiteComponent } from './tipo-tramite.component';

describe('TipoTramiteComponent', () => {
  let component: TipoTramiteComponent;
  let fixture: ComponentFixture<TipoTramiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TipoTramiteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TipoTramiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
