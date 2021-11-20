import { temporaryAllocator } from '@angular/compiler/src/render3/view/util';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
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

  sendSingleEmailValidation() {
    this.openSendEmailsDialog('EmailValidation');
    this.dialogRef.close();
  }

  sendSingleWishLink() {
    this.openSendEmailsDialog('WishLink');
    this.dialogRef.close();
  }

  sendSingleTargetGiftMessage() {
    this.openSendEmailsDialog('TargetGiftMessage');
    this.dialogRef.close();
  }

  openSendEmailsDialog(templateId: string) {
    this.dialog.open(EmailSenderPopupComponent, {
      data: {recipients: [this.participant], templateId}
    });
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
