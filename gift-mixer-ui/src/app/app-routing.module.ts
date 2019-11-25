import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DariloComponent } from './darilo/darilo.component';
import { KoncanoComponent } from './koncano/koncano.component';
import { MissingComponent } from './missing/missing.component';


const routes: Routes = [
  { path: 'darilo/:userIdCode', component: DariloComponent },
  { path: 'koncano', component: KoncanoComponent },
  { path: '**', component: MissingComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
