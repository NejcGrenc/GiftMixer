import { Injectable } from '@angular/core';
import { RestServiceComponent } from 'src/app/rest-service/rest-service.component';
import { Observable } from 'rxjs';
import { ParticipantCode } from '../../model/participant.model';

@Injectable({
  providedIn: 'root'
})
export class PrivateDataService {

  constructor(
    private rest: RestServiceComponent,
  ) { }

  public fetchParticipantPrivateData(participantId: number): Observable<ParticipantCode> {
    return this.rest.fetch<ParticipantCode>('/participantPrivateData', participantId);
  }

}
