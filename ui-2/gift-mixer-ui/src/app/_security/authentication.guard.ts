import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, RouterStateSnapshot, CanActivate, Router } from '@angular/router';
import { AuthenticationService } from './authentication.service';
import { map, catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { environment } from 'src/environments/environment';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationGuard implements CanActivate {

  private useExternalValidation = false;
  private helper = new JwtHelperService();

  constructor(
    private httpClient: HttpClient,
    private authenticationService: AuthenticationService,
    private router: Router
  ) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> {

    // Request contains newly generated JWT token
    // This request is a redirect from the login service
    if (route.queryParams[environment.loginGeneratedJwtParam]) {
      const newlyGeneratedJwtToken = route.queryParams[environment.loginGeneratedJwtParam];
      this.authenticationService.authJwtToken = newlyGeneratedJwtToken;

      // Remove jwt query param
      const queryParams = JSON.parse(JSON.stringify(route.queryParams));
      queryParams[environment.loginGeneratedJwtParam] = null;
      this.router.navigate([route.routeConfig.path], { queryParams });
    }

    const jwtToken = this.authenticationService.authJwtToken;
    if (! jwtToken) {
      this.redirectToLogin(state.url);
      return of(false);
    }

    if (! this.useExternalValidation) {
      const decodedToken = this.helper.decodeToken(jwtToken);
      const expirationDate = this.helper.getTokenExpirationDate(jwtToken);
      const isExpired = this.helper.isTokenExpired(jwtToken);

      // save username if not yet present
      if (decodedToken && ! this.authenticationService.user) {
        this.authenticationService.user = decodedToken.sub;
      }

      if (isExpired) {
        this.redirectToLogin(state.url);
      }
      return of(true);

    } else {
      return this.httpClient.post<ValidationResponseMessage>(environment.validationUrl, {jwtToken})
      .pipe(
        map(response => {
          if (! response.valid) {
            this.redirectToLogin(state.url);
          } else {
            // save username if not yet present
            if (! this.authenticationService.user) {
              this.authenticationService.user = response.username;
            }
            return true;
          }
        }),
        catchError(() => {
          this.redirectToLogin(state.url);
          return of(false);
        })
      );
    }
  }

  redirectToLogin(returnUrl: string) {
    window.location.href = `${environment.loginUrl}?${environment.loginReturnUrlParam}=${window.location.origin}${returnUrl}`;
  }

}

class ValidationResponseMessage {
  public valid: boolean;
  public username: string;
  public newToken: string;
  public errorMessage: string;
}
