import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MissingComponent } from './missing/missing.component';
import { RestServiceComponent } from './rest-service/rest-service.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { ReactiveFormsModule } from '@angular/forms';
import { MatFormFieldModule  } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatDialogModule } from '@angular/material/dialog';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';


import { AdminComponent } from './admin/admin.component';
import { InfoComponent } from './info/info.component';
import { AuthenticationInterceptor } from './_security/authentication.interceptor';
import { ParticipantsComponent } from './admin/participants/participants.component';
import { PrivateDataPopupComponent } from './admin/participants/private-data-popup/private-data-popup.component';
import { SingleMessagePopupComponent } from './admin/participants/single-message-popup/single-message-popup.component';
import { PismoComponent } from './participant-pages/pismo/pismo.component';
import { PismoPopupComponent } from './participant-pages/pismo/pismo-popup/pismo-popup.component';
import { VerificationComponent } from './participant-pages/verification/verification.component';
import { ChainShowPopupComponent } from './admin/chain-show-popup/chain-show-popup.component';
import { EmailSenderPopupComponent } from './admin/email-sender-popup/email-sender-popup.component';


@NgModule({
  declarations: [
    AppComponent,

    PismoComponent,
    MissingComponent,
    AdminComponent,
    InfoComponent,
    ParticipantsComponent,
    PrivateDataPopupComponent,
    SingleMessagePopupComponent,
    PismoPopupComponent,
    VerificationComponent,
    EmailSenderPopupComponent,
    ChainShowPopupComponent,

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    MatButtonModule,
    MatTableModule,
    MatDialogModule,
    MatIconModule,
    MatFormFieldModule,
    MatInputModule,
    ReactiveFormsModule,
    MatProgressSpinnerModule,

    HttpClientModule,
    FormsModule,
    BrowserAnimationsModule
  ],
  providers: [
    RestServiceComponent,

    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    }
  ],
  entryComponents: [
    PrivateDataPopupComponent,
    SingleMessagePopupComponent,
    EmailSenderPopupComponent,
    PismoPopupComponent,
    ChainShowPopupComponent,
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
