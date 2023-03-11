import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaciltyCreateComponent } from './facilty-create.component';

describe('FuramaCreateComponent', () => {
  let component: FaciltyCreateComponent;
  let fixture: ComponentFixture<FaciltyCreateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FaciltyCreateComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FaciltyCreateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
