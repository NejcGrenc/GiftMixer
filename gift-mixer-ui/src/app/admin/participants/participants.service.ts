import { Participant } from '../model/participant.model';
import { Injectable } from '@angular/core';
import { RestServiceComponent } from 'src/app/rest-service/rest-service.component';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParticipantsService {

  constructor(
    private rest: RestServiceComponent,
  ) { }

  public fetchParticipants(): Observable<Participant[]> {
    return this.rest.fetch<Participant[]>('/allParticipants', null);
  }

  public newParticipant(name: string, email: string): Observable<Participant> {
    const newParticipantRequest = { name, email };
    return this.rest.fetch<Participant>('/newParticipant', newParticipantRequest);
  }

  public editParticipant(editParticipant: Participant): Observable<Participant> {
    return this.rest.fetch<Participant>('/editParticipant', editParticipant);
  }

  public removeParticipant(id: number): Observable<void> {
    return this.rest.fetch<void>('/removeParticipant', id);
  }
}
