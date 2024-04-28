import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TramiteComponent } from './tramite.component';

describe('TramiteComponent', () => {
  let component: TramiteComponent;
  let fixture: ComponentFixture<TramiteComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [TramiteComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(TramiteComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
