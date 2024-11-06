import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { EmailSenderService } from '../../email-sender-popup/email-sender.service';
import { Participant } from '../../model/participant.model';

@Component({
  selector: 'app-email-sender-popup',
  templateUrl: './single-test-message-popup.component.html',
  styleUrls: ['./single-test-message-popup.component.scss']
})
export class SingleTestMessagePopupComponent implements OnInit {

  templateId = 'TestEmail';

  loading = false;
  sent = false;

  participant: Participant;

  sentMessages: number = null;


  constructor(
    public dialogRef: MatDialogRef<SingleTestMessagePopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private emailSenderService: EmailSenderService
  ) { }

  ngOnInit() {
    console.log(this.data);
    this.participant = this.data.participant;
  }

  public sendTestEmail() {
    console.log('sending email', this.templateId, this.participant);
    this.emailSenderService.sendTestEmail(this.participant).subscribe((sentMessages) => {
      this.openResults(sentMessages);
    });
    this.loading = true;
  }

  openResults(sentMessages: number) {
    console.log('email results received', sentMessages);
    this.sentMessages = sentMessages;
    this.loading = false;
    this.sent = true;
  }

  success(): boolean {
    return this.sentMessages === 1;
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
