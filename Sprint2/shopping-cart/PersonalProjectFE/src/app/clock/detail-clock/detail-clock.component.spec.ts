import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DetailClockComponent} from './detail-clock.component';

describe('DetailProductComponent', () => {
  let component: DetailClockComponent;
  let fixture: ComponentFixture<DetailClockComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetailClockComponent]
    })
      .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailClockComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
