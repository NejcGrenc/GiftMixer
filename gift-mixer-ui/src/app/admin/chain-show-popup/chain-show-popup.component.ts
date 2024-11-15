import { Chain } from '../model/chain.model';
import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';

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

  ngOnInit(): void {
    this.chain = this.data.chain as Chain;
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }

}
