import { PismoPopupComponent } from './pismo-popup/pismo-popup.component';
import { Component, OnInit, Input } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { MatDialog } from '@angular/material/dialog';
import { Participant } from 'src/app/admin/model/participant.model';
import { ParticipantsService } from 'src/app/admin/participants/participants.service';
import { RestServiceComponent } from 'src/app/rest-service/rest-service.component';

@Component({
  selector: 'app-pismo',
  templateUrl: './pismo.component.html',
  styleUrls: ['./pismo.component.scss']
})
export class PismoComponent implements OnInit {

  // private readonly verifyUser_successMessage: string = "Uporabnik prepoznan!";
  // private readonly verifyUser_errorMessage: string = "Neznan uporabnik. Ali pa je bila vnešena nepravlina varnostna koda.";
  // private readonly setWish_successMessage: string = "Želje so uspešno shranjene!";
  // private readonly setWish_errorMessage: string = "Zgodila se je nepričakovana napaka.
  //                                            \nLahko se zgodi, da se podatki izgubijo, zato raje napravi rezervno kopijo!";
  // private readonly getWish_successMessage: string = "Želje so uspešno prebrane!";
  // private readonly getWish_errorMessage: string = "Želja ni bilo mogoče najti!";

  participantCode: string;
  participant: Participant;

  get userVerified(): boolean {
    return this.participant !== undefined && this.participant !== null;
  }

  wish: string;

  constructor(
    private titleService: Title,
    private rest: RestServiceComponent,
    private route: ActivatedRoute,
    private participantsService: ParticipantsService,
    private dialog: MatDialog,
  ) { }

  ngOnInit() {
    this.titleService.setTitle('Pismo');
    this.route.params.subscribe(params => {

      this.participantCode = params.participantCode;
      this.participantsService.fetchParticipantByCode(this.participantCode).subscribe(participant => {
        this.participant = participant;

        this.loadWish(participant.id);
      });
    });
  }

  saveWish(): void {
    console.log('Sending wish for user:', this.participant.id);

    const request = {
      participantId: this.participant.id,
      wishContent: this.wish
    };

    this.rest.send('/saveWish', request).subscribe(
      () => {
        console.log('Wish is saved');
        this.dialog.open(PismoPopupComponent, { data: {success: true} });
      },
      (error) => {
        console.log('Wish is not saved');
        this.dialog.open(PismoPopupComponent, { data: {success: false} });
      },
    );
  }

  loadWish(participantId: number): void {
    console.log('Getting wish for user:', participantId);

    this.rest.fetch<WishResponse>('/loadWish', this.participant.id).subscribe((response: WishResponse) => {
      console.log(response);
      this.wish = response.wishContent;
    });
  }

}

export class WishResponse {
  participantId: string;
  wishContent: string;
}
