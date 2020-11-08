import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DariloComponent } from './darilo/darilo.component';
import { KoncanoComponent } from './koncano/koncano.component';
import { MissingComponent } from './missing/missing.component';
import { RestServiceComponent } from './rest-service/rest-service.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatButtonModule } from '@angular/material/button';
import { AdminComponent } from './admin/admin.component';
import { InfoComponent } from './info/info.component';
import { AuthenticationInterceptor } from './_security/authentication.interceptor';

@NgModule({
  declarations: [
    AppComponent,
        
    DariloComponent,
    KoncanoComponent,
    MissingComponent,
    AdminComponent,
    InfoComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,  
    MatButtonModule,
      
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
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
