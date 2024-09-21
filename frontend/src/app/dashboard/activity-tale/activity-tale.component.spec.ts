import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ActivityTaleComponent } from './activity-tale.component';

describe('ActivityTaleComponent', () => {
  let component: ActivityTaleComponent;
  let fixture: ComponentFixture<ActivityTaleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ActivityTaleComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ActivityTaleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
