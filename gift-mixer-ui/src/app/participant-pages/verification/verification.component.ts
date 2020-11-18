import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { Title } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { Participant } from 'src/app/admin/model/participant.model';
import { ParticipantsService } from 'src/app/admin/participants/participants.service';
import { RestServiceComponent } from 'src/app/rest-service/rest-service.component';

@Component({
  selector: 'app-verification',
  templateUrl: './verification.component.html',
  styleUrls: ['./verification.component.scss']
})
export class VerificationComponent implements OnInit {

  participantCode: string;
  participant: Participant;

  successfulVerification = false;

  constructor(
    private titleService: Title,
    private rest: RestServiceComponent,
    private route: ActivatedRoute,
    private participantsService: ParticipantsService,
  ) { }

  ngOnInit() {
    this.titleService.setTitle('Potrditev');
    this.route.params.subscribe(params => {

      this.participantCode = params.participantCode;

      this.participantsService.fetchParticipantByCode(this.participantCode).subscribe(participant => {
        this.participant = participant;
      });

      this.verifyParticipantCode();
    });
  }

  verifyParticipantCode(): void {
    console.log('Verification');

    this.rest.send('/verifyUser', this.participantCode).subscribe(
      () => {
        console.log('Successful verification');
        this.successfulVerification = true;
      },
      (error) => {
        console.log('Unsuccessful verification');
        this.successfulVerification = false;
      },
    );
  }
}
