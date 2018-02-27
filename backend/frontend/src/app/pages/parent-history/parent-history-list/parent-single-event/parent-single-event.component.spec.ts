import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentSingleEventComponent } from './parent-single-event.component';

describe('ParentSingleEventComponent', () => {
  let component: ParentSingleEventComponent;
  let fixture: ComponentFixture<ParentSingleEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentSingleEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentSingleEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
