import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentEventsComponent } from './parent-events.component';

describe('ParentEventsComponent', () => {
  let component: ParentEventsComponent;
  let fixture: ComponentFixture<ParentEventsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentEventsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
