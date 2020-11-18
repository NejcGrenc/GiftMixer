import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MatProgressSpinnerModule, MAT_DIALOG_DATA } from '@angular/material';
import { Participant } from '../model/participant.model';
import { EmailSenderService } from './email-sender.service';


@Component({
  selector: 'app-email-sender-popup',
  templateUrl: './email-sender-popup.component.html',
  styleUrls: ['./email-sender-popup.component.scss']
})
export class EmailSenderPopupComponent implements OnInit {

  loading = false;
  sent = false;

  recipients: Participant[];
  templateId: string;

  sentMessages: number = null;


  constructor(
    public dialogRef: MatDialogRef<EmailSenderPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private emailSenderService: EmailSenderService
  ) { }

  ngOnInit() {
    this.recipients = this.data.recipients;
    this.templateId = this.data.templateId;
  }

  public sendEmails() {
    console.log('sending emails', this.templateId, this.recipients);
    switch (this.templateId) {
      case 'EmailValidation':
        this.emailSenderService.sendVerificationEmail(this.recipients).subscribe((sentMessages) => {
          this.openResults(sentMessages);
        });
        break;
      case 'WishLink':
        this.emailSenderService.sendWishInvitationEmail(this.recipients).subscribe((sentMessages) => {
          this.openResults(sentMessages);
        });
        break;
      case 'TargetGiftMessage':
        this.emailSenderService.sendTargetGiftEmail(this.recipients).subscribe((sentMessages) => {
          this.openResults(sentMessages);
        });
        break;
    }
    this.loading = true;
  }

  openResults(sentMessages: number) {
    console.log('email results received', sentMessages);
    this.sentMessages = sentMessages;
    this.loading = false;
    this.sent = true;
  }

  success(): boolean {
    return this.sentMessages === this.recipients.length;
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
