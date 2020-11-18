import { Chain } from './model/chain.model';
import { RestServiceComponent } from './../rest-service/rest-service.component';
import { AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AuthenticationService } from '../_security/authentication.service';
import { AdminService } from './admin.service';
import { GiftMixerAdmin } from './model/admin.model';
import { Participant } from './model/participant.model';
import { ParticipantsComponent } from './participants/participants.component';
import { ChainShowPopupComponent } from './chain-show-popup/chain-show-popup.component';
import { EmailSenderPopupComponent } from './email-sender-popup/email-sender-popup.component';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit, AfterViewInit {

  admin: GiftMixerAdmin = null;

  @ViewChild(ParticipantsComponent, {static: false})
  public participantsComponent: ParticipantsComponent;

  get participants(): Participant[] {
    return (this.participantsComponent === undefined || this.participantsComponent === null) ? [] : this.participantsComponent.participants;
  }

  chain: Chain = null;

  constructor(
    public authenticationService: AuthenticationService,
    public adminService: AdminService,
    private rest: RestServiceComponent,
    private changeDetector: ChangeDetectorRef,
    private dialog: MatDialog
  ) {

    // Make sure that admin exists
    // Load admin data
    const adminUsername = this.authenticationService.user;
    adminService.creteOrVerifyAdmin(adminUsername).subscribe(admin => {
      this.admin = admin;
    });

    // Load any existing chain data
    this.loadChain();
  }

  ngOnInit() {
  }

  ngAfterViewInit(): void {


    this.changeDetector.detectChanges();
  }

  public logout() {
    this.authenticationService.logout();
  }



  public areAllMailValidated(): boolean {
    return this.participants.filter(x => !x.confirmedConfirmationEmail).length === 0;
  }

  public areAllLettersWritten(): boolean {
    return this.participants.filter(x => !x.confirmedRecievedWishLink).length === 0;
  }

  public sendAllEmailValidation() {
    this.openSendEmailsDialog('EmailValidation').subscribe(sent => {
      if (sent) {
        this.admin.alreadySentAllEmailValidation = true;
        this.updateAdmin();
      }
    });
  }

  public sendAllWishLinks() {
    this.openSendEmailsDialog('WishLink').subscribe(sent => {
      if (sent) {
        this.admin.alreadySentAllWishLinks = true;
        this.updateAdmin();
      }
    });
  }

  public sendAllTargetGiftMessages() {
    this.openSendEmailsDialog('TargetGiftMessage').subscribe(sent => {
      if (sent) {
        this.admin.alreadySentAllTargetGiftMessages = true;
        this.updateAdmin();
      }
    });
  }

  private openSendEmailsDialog(templateId: string): Observable<boolean> {
    const dialogRef = this.dialog.open(EmailSenderPopupComponent, {
      data: {recipients: this.participants, templateId},
      disableClose: true
    });
    return dialogRef.afterClosed();
  }

  private updateAdmin() {
    this.adminService.updateAdmin(this.admin).subscribe(updatedAdmin => {
      this.admin = updatedAdmin;
    });
  }



  makeChain() {
    this.adminService.makeChain().subscribe(chain => {
      this.chain = chain;
    });
  }

  loadChain() {
    this.adminService.loadChain().subscribe(chain => {
      this.chain = chain;
    });
  }

  showChain() {
    this.dialog.open(ChainShowPopupComponent, {
      data: {chain: this.chain}
    });
  }

}
