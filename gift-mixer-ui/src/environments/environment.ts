// This file can be replaced during build by using the `fileReplacements` array.
// `ng build --prod` replaces `environment.ts` with `environment.prod.ts`.
// The list of file replacements can be found in `angular.json`.

export const environment = {
  production: false,

  backendBaseUrl: 'http://localhost:9014/',

  validationUrl: 'http://localhost:9010/validate',

  loginUrl: 'http://localhost:4010/login',
  logoutUrl: 'http://localhost:4010/logout',
  loginReturnUrlParam: 'returnUrl',
  loginGeneratedJwtParam: 'jwtToken',

  // expiresAt: Thu Oct 03 14:42:22 CEST 2030,
  useDevelopmentUser: true,
  developmentUser: 'local-user',
  developmentJWT: 'eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJsb2NhbC11c2VyIiwiZXhwIjoxOTE3MjYxNzQyLCJpYXQiOjE2MDE5MDE3NDJ9.PRljUVXSQoW4UoVzrPn7RSC-db05S_pfKEAjcAUarWzeKhHSmwv53Pt-LAQzXztnsE5ZoHdtu-ZmcFZdzgUfYw',

};

/*
 * For easier debugging in development mode, you can import the following file
 * to ignore zone related error stack frames such as `zone.run`, `zoneDelegate.invokeTask`.
 *
 * This import should be commented out in production mode because it will have a negative impact
 * on performance if an error is thrown.
 */
// import 'zone.js/dist/zone-error';  // Included with Angular CLI.
