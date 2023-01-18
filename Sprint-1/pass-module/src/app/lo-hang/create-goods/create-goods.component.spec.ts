import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CreateGoodsComponent } from './create-goods.component';

describe('NhapHangComponent', () => {
  let component: CreateGoodsComponent;
  let fixture: ComponentFixture<CreateGoodsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateGoodsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateGoodsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
