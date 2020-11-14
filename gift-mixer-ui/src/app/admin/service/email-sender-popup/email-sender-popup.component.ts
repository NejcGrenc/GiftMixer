import { Participant } from './../../model/participant.model';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { EmailSenderService } from '../email-sender.service';

@Component({
  selector: 'app-email-sender-popup',
  templateUrl: './email-sender-popup.component.html',
  styleUrls: ['./email-sender-popup.component.scss']
})
export class EmailSenderPopupComponent implements OnInit {

  recipients: Participant[];
  templateId: string;

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
    this.emailSenderService.sendEmail(this.recipients, this.templateId);
    this.dialogRef.close();
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
