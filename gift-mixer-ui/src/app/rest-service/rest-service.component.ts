import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {environment} from '../../environments/environment';

@Injectable()
export class RestServiceComponent {

  constructor(private http: HttpClient) { }

  get<T>(path: string): Observable<T> {
    return this.http.get<T>(environment.backendBaseUrl + path);
  }

  send(path: string, data: any): Observable<void> {
    return this.http.post<void>(environment.backendBaseUrl + path, data);
  }

  fetch<T>(path: string, data: any): Observable<T> {
    return this.http.post<T>(environment.backendBaseUrl + path, data);
  }
}

export class RestResponse<T> {
  success: boolean;
  value: T;
}
