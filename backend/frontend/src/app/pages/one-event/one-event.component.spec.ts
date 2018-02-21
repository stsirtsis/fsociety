import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OneEventComponent } from './one-event.component';

describe('OneEventComponent', () => {
  let component: OneEventComponent;
  let fixture: ComponentFixture<OneEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OneEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OneEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
