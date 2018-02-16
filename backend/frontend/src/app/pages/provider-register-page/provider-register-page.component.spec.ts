import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ProviderRegisterPageComponent } from './provider-register-page.component';

describe('ProviderRegisterPageComponent', () => {
  let component: ProviderRegisterPageComponent;
  let fixture: ComponentFixture<ProviderRegisterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ProviderRegisterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ProviderRegisterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
