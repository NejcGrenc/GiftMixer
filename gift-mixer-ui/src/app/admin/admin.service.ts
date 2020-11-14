import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RestServiceComponent } from '../rest-service/rest-service.component';
import { GiftMixerAdmin } from './model/admin.model';


@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(
    private rest: RestServiceComponent
  ) { }

  loadAdmin(username: string): Observable<GiftMixerAdmin> {
    return this.rest.fetch<GiftMixerAdmin>('/admin', { username });
  }

}
