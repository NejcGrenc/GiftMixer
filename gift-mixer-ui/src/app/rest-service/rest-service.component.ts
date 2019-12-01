import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class RestServiceComponent {

  constructor(private http: HttpClient) { }

//  baseUrl = 'http://localhost:9000';
  baseUrl = 'http://grenc.eu:9000';
  
  send(path: string, data: any): Observable<boolean> {
    return this.http.post<boolean>(this.baseUrl + path, data);
  }
  
  fetch<T>(path: string, data: any): Observable<RestResponse<T>> {
    return this.http.post<RestResponse<T>>(this.baseUrl + path, data);
  }
}

export class RestResponse<T> {
  success: boolean;
  value: T;
}
