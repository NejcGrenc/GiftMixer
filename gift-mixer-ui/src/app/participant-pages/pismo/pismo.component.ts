import { PismoPopupComponent } from './pismo-popup/pismo-popup.component';
import { Component, OnInit, Input, ViewChild, ElementRef, ViewEncapsulation } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { MatDialog } from '@angular/material/dialog';
import { Participant } from 'src/app/admin/model/participant.model';
import { ParticipantsService } from 'src/app/admin/participants/participants.service';
import { RestServiceComponent } from 'src/app/rest-service/rest-service.component';
import { HostListener } from '@angular/core';
import { Renderer2 } from '@angular/core';

@Component({
  selector: 'app-pismo',
  templateUrl: './pismo.component.html',
  styleUrls: ['./pismo.component.scss'],
  encapsulation: ViewEncapsulation.None,
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
    private renderer: Renderer2
  ) { }

  private quill: ElementRef;
  @ViewChild('quill', {static: false}) set content(content: ElementRef) {
    console.log('try', content);
    if (content) { // initially setter gets called with undefined
        this.quill = content;
    }
 }

  @HostListener('document:mousemove', ['$event'])
  onMouseMove($event) {
    const x = $event.clientX;
    const y = $event.clientY;
    if (this.quill) {
      this.renderer.setStyle( this.quill.nativeElement, 'left', x + 'px');
      this.renderer.setStyle( this.quill.nativeElement, 'top',  y + 'px');
    }
  }

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
