import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ListConsignment } from './list-consignment';

describe('DanhSachLoHangComponent', () => {
  let component: ListConsignment;
  let fixture: ComponentFixture<ListConsignment>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ListConsignment ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(ListConsignment);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
