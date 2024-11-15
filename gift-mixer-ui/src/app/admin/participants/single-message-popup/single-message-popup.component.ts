import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialog, MatDialogRef } from '@angular/material/dialog';
import { EmailSenderPopupComponent } from '../../email-sender-popup/email-sender-popup.component';
import { Participant } from '../../model/participant.model';

@Component({
  selector: 'app-single-message-popup',
  templateUrl: './single-message-popup.component.html',
  styleUrls: ['./single-message-popup.component.scss']
})
export class SingleMessagePopupComponent implements OnInit {

  participant: Participant;

  constructor(
    public dialog: MatDialog,
    public dialogRef: MatDialogRef<SingleMessagePopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    this.participant = this.data.participant;
  }

  sendSingleEmailValidation(): void {
    this.openSendEmailsDialog('EmailValidation');
    this.dialogRef.close();
  }

  sendSingleWishLink(): void {
    this.openSendEmailsDialog('WishLink');
    this.dialogRef.close();
  }

  sendSingleTargetGiftMessage(): void {
    this.openSendEmailsDialog('TargetGiftMessage');
    this.dialogRef.close();
  }

  openSendEmailsDialog(templateId: string): void {
    this.dialog.open(EmailSenderPopupComponent, {
      data: {recipients: [this.participant], templateId}
    });
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }
}
