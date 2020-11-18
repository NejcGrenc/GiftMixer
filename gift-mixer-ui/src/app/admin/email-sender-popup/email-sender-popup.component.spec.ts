import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { EmailSenderPopupComponent } from './email-sender-popup.component';

describe('EmailSenderPopupComponent', () => {
  let component: EmailSenderPopupComponent;
  let fixture: ComponentFixture<EmailSenderPopupComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ EmailSenderPopupComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(EmailSenderPopupComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
