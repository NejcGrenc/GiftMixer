import { AdminComponent } from './admin/admin.component';
import { InfoComponent } from './info/info.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { KoncanoComponent } from './koncano/koncano.component';
import { MissingComponent } from './missing/missing.component';
import { AuthenticationGuard } from './_security/authentication.guard';
import { PismoComponent } from './pismo/pismo.component';


const routes: Routes = [
  { path: 'info', component: InfoComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AuthenticationGuard] },

  { path: 'pismo/:participantCode', component: PismoComponent },
  { path: 'koncano', component: KoncanoComponent },
  { path: '**', component: MissingComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
