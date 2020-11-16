import { Router } from '@angular/router';
import { Participant } from './../../model/participant.model';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { PrivateDataService } from './private-data.service';

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

  constructor(
    public dialogRef: MatDialogRef<PrivateDataPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private privateDataService: PrivateDataService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.participant = this.data.participant as Participant;

    this.privateDataService.fetchParticipantPrivateData(this.participant.id).subscribe(response => {
      this.participantCode = response.code;
    });

    this.recieverFromName = 'Nejc';
    this.giverToName = 'Kaja';
  }

  navigateToWish(): void {
    this.dialogRef.close();
    this.router.navigate(['/pismo/' + this.participantCode]);
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
