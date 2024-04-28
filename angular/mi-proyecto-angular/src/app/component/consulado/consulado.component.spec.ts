import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsuladoComponent } from './consulado.component';

describe('ConsuladoComponent', () => {
  let component: ConsuladoComponent;
  let fixture: ComponentFixture<ConsuladoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsuladoComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConsuladoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
