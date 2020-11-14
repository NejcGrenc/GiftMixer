import { Participant } from './../../model/participant.model';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-private-data-popup',
  templateUrl: './private-data-popup.component.html',
  styleUrls: ['./private-data-popup.component.scss']
})
export class PrivateDataPopupComponent implements OnInit {

  privateDataHidden = true;

  participant: Participant;
  participantCode: string;
  recieverFromName: string;
  giverToName: string;

  get linkToWish(): string {
    return 'link: ' + this.participantCode;
  }

  constructor(
    public dialogRef: MatDialogRef<PrivateDataPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) { }

  ngOnInit(): void {
    this.participant = this.data.participant;
    this.participantCode = '123123';
    this.recieverFromName = 'Nejc';
    this.giverToName = 'Kaja';  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
