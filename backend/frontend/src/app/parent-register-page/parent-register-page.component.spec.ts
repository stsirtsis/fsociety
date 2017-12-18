import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentRegisterPageComponent } from './parent-register-page.component';

describe('ParentRegisterPageComponent', () => {
  let component: ParentRegisterPageComponent;
  let fixture: ComponentFixture<ParentRegisterPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentRegisterPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentRegisterPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
