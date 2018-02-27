import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentHistoryComponent } from './parent-history.component';

describe('ParentHistoryComponent', () => {
  let component: ParentHistoryComponent;
  let fixture: ComponentFixture<ParentHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
