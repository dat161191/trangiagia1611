import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ClockCreateComponent} from './clock-create.component';

describe('ProductCreateComponent', () => {
  let component: ClockCreateComponent;
  let fixture: ComponentFixture<ClockCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ClockCreateComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ClockCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
