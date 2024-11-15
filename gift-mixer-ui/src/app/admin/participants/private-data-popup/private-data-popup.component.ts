import { Router } from '@angular/router';
import { Participant } from '../../model/participant.model';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { PrivateDataService } from './private-data.service';
import { AdminService } from '../../admin.service';

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
    private adminService: AdminService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.participant = this.data.participant as Participant;

    this.privateDataService.fetchParticipantPrivateData(this.participant.id).subscribe(response => {
      this.participantCode = response.code;
    });

    this.adminService.loadChain().subscribe((chain) => {
      if (chain === null || chain.pairs === null || chain.pairs.length === 0) {
        this.recieverFromName = '/ (Pari še niso izbrani)';
        this.giverToName = '/ (Pari še niso izbrani)';

      } else {
        for (const pair of chain.pairs) {
          if (pair.giverId === this.participant.id) {
            this.giverToName = pair.receiverName;
          }
          if (pair.receiverId === this.participant.id) {
            this.recieverFromName = pair.giverName;
          }
        }
      }
    });
  }

  navigateToWish(): void {
    this.dialogRef.close();
    this.router.navigate(['/pismo/' + this.participantCode]).then();
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
