import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParentRegisterComponentComponent } from './parent-register-component.component';

describe('ParentRegisterComponentComponent', () => {
  let component: ParentRegisterComponentComponent;
  let fixture: ComponentFixture<ParentRegisterComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParentRegisterComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParentRegisterComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
