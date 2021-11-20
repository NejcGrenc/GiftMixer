import { Participant } from './../model/participant.model';
import { Injectable } from '@angular/core';
import { RestServiceComponent } from 'src/app/rest-service/rest-service.component';
import { AdminService } from '../admin.service';
import { Observable } from 'rxjs';
import { MatDialog } from '@angular/material/dialog';

@Injectable({
  providedIn: 'root'
})
export class EmailSenderService {

  constructor(
    private rest: RestServiceComponent,
    public dialog: MatDialog,
    public adminService: AdminService,
  ) { }

  sendVerificationEmail(receivers: Participant[]): Observable<number> {
    return this.sendBulkMessages(receivers, '/sendEmail_verification');
  }

  sendWishInvitationEmail(receivers: Participant[]): Observable<number> {
    return this.sendBulkMessages(receivers, '/sendEmail_wishInvitation');
  }

  sendTargetGiftEmail(receivers: Participant[]): Observable<number> {
    return this.sendBulkMessages(receivers, '/sendEmail_targetGift');
  }

  private sendBulkMessages(receivers: Participant[], address: string): Observable<number> {
    const receiverIds = receivers.map(receiver => receiver.id);
    return this.rest.fetch<number>(address, receiverIds);
  }

}
