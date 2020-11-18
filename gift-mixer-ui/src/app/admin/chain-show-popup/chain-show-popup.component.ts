import { Chain } from './../model/chain.model';
import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Participant } from '../model/participant.model';

@Component({
  selector: 'app-chain-show-popup',
  templateUrl: './chain-show-popup.component.html',
  styleUrls: ['./chain-show-popup.component.scss']
})
export class ChainShowPopupComponent implements OnInit {

  privateDataHidden = true;

  chain: Chain = null;

  constructor(
    public dialogRef: MatDialogRef<ChainShowPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.chain = this.data.chain as Chain;
    console.log("sc", this.chain);
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
