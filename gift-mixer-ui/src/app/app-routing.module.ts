import { AdminComponent } from './admin/admin.component';
import { InfoComponent } from './info/info.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DariloComponent } from './darilo/darilo.component';
import { KoncanoComponent } from './koncano/koncano.component';
import { MissingComponent } from './missing/missing.component';
import { AuthenticationGuard } from './_security/authentication.guard';


const routes: Routes = [
  { path: 'info', component: InfoComponent },
  { path: 'admin', component: AdminComponent, canActivate: [AuthenticationGuard] },

  { path: 'darilo/:userIdCode', component: DariloComponent },
  { path: 'koncano', component: KoncanoComponent },
  { path: '**', component: MissingComponent }

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
