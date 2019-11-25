import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DariloComponent } from './darilo/darilo.component';
import { KoncanoComponent } from './koncano/koncano.component';
import { MissingComponent } from './missing/missing.component';
import { RestServiceComponent } from './rest-service/rest-service.component';

@NgModule({
  declarations: [
    AppComponent,
        
    DariloComponent,
    KoncanoComponent,
    MissingComponent
    
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,  
      
    HttpClientModule,
    FormsModule
  ],
  providers: [
    RestServiceComponent
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule { }
