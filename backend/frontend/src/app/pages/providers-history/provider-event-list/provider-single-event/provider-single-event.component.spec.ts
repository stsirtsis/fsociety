import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProviderSingleEventComponent } from './provider-single-event.component';

describe('ProviderSingleEventComponent', () => {
  let component: ProviderSingleEventComponent;
  let fixture: ComponentFixture<ProviderSingleEventComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProviderSingleEventComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProviderSingleEventComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
