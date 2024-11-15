import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SingleMessagePopupComponent } from './single-message-popup.component';

describe('SingleMessagePopupComponent', () => {
  let component: SingleMessagePopupComponent;
  let fixture: ComponentFixture<SingleMessagePopupComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ SingleMessagePopupComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SingleMessagePopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
