import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
  selector: 'app-pismo-popup',
  templateUrl: './pismo-popup.component.html',
  styleUrls: ['./pismo-popup.component.scss']
})
export class PismoPopupComponent implements OnInit {

  success: boolean;

  constructor(
    public dialogRef: MatDialogRef<PismoPopupComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
  ) { }

  ngOnInit() {
    this.success = this.data.success;
  }

  onCloseClick(): void {
    this.dialogRef.close();
  }
}
