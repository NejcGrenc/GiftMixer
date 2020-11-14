import { AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { AuthenticationService } from '../_security/authentication.service';
import { AdminService } from './admin.service';
import { GiftMixerAdmin } from './model/admin.model';
import { Participant } from './model/participant.model';
import { ParticipantsComponent } from './participants/participants.component';
import { EmailSenderPopupComponent } from './service/email-sender-popup/email-sender-popup.component';


@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit, AfterViewInit {

  admin: GiftMixerAdmin;

  @ViewChild(ParticipantsComponent, {static: false})
  public participantsComponent: ParticipantsComponent;

  get participants(): Participant[] {
    return (this.participantsComponent === undefined || this.participantsComponent === null) ? [] : this.participantsComponent.participants;
  }

  constructor(
    public authenticationService: AuthenticationService,
    private adminService: AdminService,
    private changeDetector: ChangeDetectorRef,
    private dialog: MatDialog
  ) { }


  ngOnInit() {
    // Load admin data
    this.admin = new GiftMixerAdmin(123, 'Nastja', false, false, false, '', '', '');
    const adminUsername = this.authenticationService.user;
    this.adminService.loadAdmin(adminUsername).subscribe(admin => {
      this.admin = admin;
    });
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
    this.openSendEmailsDialog('EmailValidation');
    this.admin.alreadySentAllEmailValidation = true;
  }

  public sendAllWishLinks() {
    this.openSendEmailsDialog('WishLink');
    this.admin.alreadySentAllWishLinks = true;
  }

  public sendAllTargetGiftMessages() {
    this.openSendEmailsDialog('TargetGiftMessage');
    this.admin.alreadySentAllTargetGiftMessages = true;
  }

  private openSendEmailsDialog(templateId: string) {
    this.dialog.open(EmailSenderPopupComponent, {
      data: {recipients: this.participants, templateId}
    });
  }

}
