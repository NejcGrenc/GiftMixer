import { environment } from '../../environments/environment';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  private localStorageJWT = 'jwtLoggedUser';
  private localStorageUser = 'loggedUsername';

  constructor(private router: Router) { }

  get authJwtToken(): string {
    if (environment.useDevelopmentUser) {
      return environment.developmentJWT;
    }

    return sessionStorage.getItem(this.localStorageJWT);
  }

  set authJwtToken(jwtToken: string) {
    if (environment.useDevelopmentUser) { return; }

    if (jwtToken === null) {
      sessionStorage.removeItem(this.localStorageJWT);
    } else {
      sessionStorage.setItem(this.localStorageJWT, jwtToken);
    }
  }


  get user(): string {
    if (environment.useDevelopmentUser) {
      return environment.developmentUser;
    }

    return sessionStorage.getItem(this.localStorageUser);
  }

  set user(user: string) {
    if (environment.useDevelopmentUser) { return; }

    if (user === null) {
      sessionStorage.removeItem(this.localStorageUser);
    } else {
      sessionStorage.setItem(this.localStorageUser, user);
    }
  }

  public logout(): void {
    this.authJwtToken = null;
    this.user = null;

    // Redirect to logout endpoint
    const returnUrl = this.router.url;
    window.location.href = `${environment.logoutUrl}?${environment.loginReturnUrlParam}=${window.location.origin}${returnUrl}`;
  }

}
