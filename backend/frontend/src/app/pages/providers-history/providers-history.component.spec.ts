import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProvidersHistoryComponent } from './providers-history.component';

describe('ProvidersHistoryComponent', () => {
  let component: ProvidersHistoryComponent;
  let fixture: ComponentFixture<ProvidersHistoryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProvidersHistoryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProvidersHistoryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
