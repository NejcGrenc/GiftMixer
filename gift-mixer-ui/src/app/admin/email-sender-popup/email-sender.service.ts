import { Participant } from './../model/participant.model';
import { Injectable } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { RestServiceComponent } from 'src/app/rest-service/rest-service.component';
import { AdminService } from '../admin.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailSenderService {

  constructor(
    private rest: RestServiceComponent,
    public dialog: MatDialog,
    public adminService: AdminService,
  ) { }


  sendTestEmail(receiver: Participant): Observable<number> {
    return this.sendSingleMessage(receiver, '/sendEmail_sample');
  }

  sendVerificationEmail(receivers: Participant[]): Observable<number> {
    return this.sendBulkMessages(receivers, '/sendEmail_verification');
  }

  sendWishInvitationEmail(receivers: Participant[]): Observable<number> {
    return this.sendBulkMessages(receivers, '/sendEmail_wishInvitation');
  }

  sendTargetGiftEmail(receivers: Participant[]): Observable<number> {
    return this.sendBulkMessages(receivers, '/sendEmail_targetGift');
  }

  private sendSingleMessage(receiver: Participant, address: string): Observable<number> {
    return this.rest.fetch<number>(address, receiver.id);
  }
  private sendBulkMessages(receivers: Participant[], address: string): Observable<number> {
    const receiverIds = receivers.map(receiver => receiver.id);
    return this.rest.fetch<number>(address, receiverIds);
  }

}
