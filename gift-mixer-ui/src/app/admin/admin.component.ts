import { Component, OnInit } from '@angular/core';
import { AuthenticationService } from '../_security/authentication.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) { }

  ngOnInit() {
  }

  deleteToken() {
    console.log(this.authenticationService.authJwtToken);

    this.authenticationService.authJwtToken = null;
  }
}
