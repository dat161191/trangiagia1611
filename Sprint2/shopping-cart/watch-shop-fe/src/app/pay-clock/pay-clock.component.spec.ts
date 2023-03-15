import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PayClockComponent } from './pay-clock.component';

describe('PayClockComponent', () => {
  let component: PayClockComponent;
  let fixture: ComponentFixture<PayClockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PayClockComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(PayClockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
