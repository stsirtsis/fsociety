import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EventsMarkerComponent } from './events-marker.component';

describe('EventsMarkerComponent', () => {
  let component: EventsMarkerComponent;
  let fixture: ComponentFixture<EventsMarkerComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EventsMarkerComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EventsMarkerComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
