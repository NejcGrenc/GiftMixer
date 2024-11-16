import { Chain } from './model/chain.model';
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

  creteOrVerifyAdmin(username: string): Observable<GiftMixerAdmin> {
    return this.rest.fetch<GiftMixerAdmin>('/createVerifyAdmin', { username });
  }

  loadAdmin(username: string): Observable<GiftMixerAdmin> {
    return this.rest.fetch<GiftMixerAdmin>('/admin', { username });
  }

  updateAdmin(admin: GiftMixerAdmin): Observable<GiftMixerAdmin> {
    return this.rest.fetch<GiftMixerAdmin>('/editAdmin', admin);
  }

  makeChain(): Observable<Chain> {
    return this.rest.fetch<Chain>('/makeChain', null);
  }

  loadChain(): Observable<Chain> {
    return this.rest.fetch<Chain>('/chain', null);
  }

}
