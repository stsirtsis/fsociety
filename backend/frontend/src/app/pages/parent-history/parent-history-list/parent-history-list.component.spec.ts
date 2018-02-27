import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentHistoryListComponent } from './parent-history-list.component';

describe('ParentHistoryListComponent', () => {
  let component: ParentHistoryListComponent;
  let fixture: ComponentFixture<ParentHistoryListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentHistoryListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentHistoryListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
