import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProviderEventListComponent } from './provider-event-list.component';

describe('ProviderEventListComponent', () => {
  let component: ProviderEventListComponent;
  let fixture: ComponentFixture<ProviderEventListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProviderEventListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProviderEventListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
