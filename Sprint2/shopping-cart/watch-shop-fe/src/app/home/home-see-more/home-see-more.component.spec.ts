import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HomeSeeMoreComponent } from './home-see-more.component';

describe('HomeSeeMoreComponent', () => {
  let component: HomeSeeMoreComponent;
  let fixture: ComponentFixture<HomeSeeMoreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HomeSeeMoreComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeSeeMoreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
