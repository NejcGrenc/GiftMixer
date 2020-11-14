import { Participant } from './../model/participant.model';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailSenderService {

  constructor() { }

  public sendEmail(to: Participant[], templateId: string) {
    console.log('Sending emails', to, templateId);
  }
}
