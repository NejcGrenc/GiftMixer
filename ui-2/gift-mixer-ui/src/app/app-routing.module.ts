import { VerificationComponent } from './participant-pages/verification/verification.component';
import { AdminComponent } from './admin/admin.component';
import { InfoComponent } from './info/info.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { MissingComponent } from './missing/missing.component';
import { AuthenticationGuard } from './_security/authentication.guard';
import { PismoComponent } from './participant-pages/pismo/pismo.component';


const routes: Routes = [
  { path: '', component: InfoComponent },
  { path: 'info', component: InfoComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AuthenticationGuard] },

  { path: 'pismo/:participantCode', component: PismoComponent },
  { path: 'potrditev/:participantCode', component: VerificationComponent },
  { path: '**', component: MissingComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
