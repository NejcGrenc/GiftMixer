import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable()
export class RestServiceComponent {

  constructor(private http: HttpClient) { }
  
  send(path: string, data: any): Observable<boolean> {
    return this.http.post<boolean>(environment.baseUrl + path, data);
  }
  
  fetch<T>(path: string, data: any): Observable<RestResponse<T>> {
    return this.http.post<RestResponse<T>>(environment.baseUrl + path, data);
  }
}

export class RestResponse<T> {
  success: boolean;
  value: T;
}
