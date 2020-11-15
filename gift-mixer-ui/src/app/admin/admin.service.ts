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

  sampleEmail(): void {
    console.log('samope');
    this.rest.fetch<void>('/email', null).subscribe();
  }

  makeChain(): void {
    this.rest.fetch<any>('/chain', null).subscribe(a =>
      console.log('Making chain', a));
  }

}
