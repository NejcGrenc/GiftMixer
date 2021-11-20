import { Injectable } from '@angular/core';
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthenticationService } from './authentication.service';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationInterceptor implements HttpInterceptor {

  constructor(private authenticationService: AuthenticationService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    const jwt = this.authenticationService.authJwtToken;
    if (jwt) {
      const cloned = req.clone({
          headers: req.headers.set('Authorization', 'Bearer ' + jwt)
      });
      return next.handle(cloned);
    } else {
      return next.handle(req);
    }
  }
}
