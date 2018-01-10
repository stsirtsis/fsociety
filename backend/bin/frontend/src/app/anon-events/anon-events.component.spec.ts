import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AnonEventsComponent } from './anon-events.component';

describe('AnonEventsComponent', () => {
  let component: AnonEventsComponent;
  let fixture: ComponentFixture<AnonEventsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AnonEventsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AnonEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
