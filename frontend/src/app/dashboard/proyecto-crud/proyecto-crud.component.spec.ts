import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProyectoCrudComponent } from './proyecto-crud.component';

describe('ProyectoCrudComponent', () => {
  let component: ProyectoCrudComponent;
  let fixture: ComponentFixture<ProyectoCrudComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProyectoCrudComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProyectoCrudComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
