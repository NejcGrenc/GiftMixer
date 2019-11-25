import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable()
export class RestServiceComponent {

  constructor(private http: HttpClient) { }

  baseUrl = 'http://localhost:9000';
  
  send(path: string, data: any): Observable<boolean> {
    return this.http.post<boolean>(this.baseUrl + path, data);
  }
  
  fetch(path: string, data: any): Observable<WishResponse> {
    return this.http.post<WishResponse>(this.baseUrl + path, data);
  }
}

export class WishResponse {
  success: boolean;
  wishContent: string;
}
