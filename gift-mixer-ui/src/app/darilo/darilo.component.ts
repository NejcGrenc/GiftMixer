import { Component, OnInit, Input } from '@angular/core';
import { RestServiceComponent } from '../rest-service/rest-service.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-darilo',
  templateUrl: './darilo.component.html',
  styleUrls: ['./darilo.component.scss']
})
export class DariloComponent implements OnInit {

  private readonly setWish_successMessage: string = "Želje so uspešno shranjene!";
  private readonly setWish_errorMessage: string = "Zgodila se je nepričakovana napaka. \nLahko se zgodi, da se podatki izgubijo, zato raje napravi rezervno kopijo!";
  private readonly verifyUser_successMessage: string = "Uporabnik prepoznan!";
  private readonly verifyUser_errorMessage: string = "Neznan uporabnik. Ali pa je bila vnešena nepravlina varnostna koda.";

  private userId: string;
  private userVerified: boolean;

  constructor(private rest: RestServiceComponent, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let userIdCode = params['userIdCode'];
      this.verifyUser(userIdCode);
    });
  }
  
  private verifyUser(userIdCode: string): void {
    let user = userIdCode.split('-')[0];
    let code = userIdCode.split('-')[1];
    this.userId = user;
    console.log("Verificating user:", user, code);
    
    this.rest.send("/verifyUser", {userName: user, userCode: code}).subscribe(
      (response: Boolean)=>{
        if (response) {
          console.log(this.verifyUser_successMessage, this.userId);
        } else {
          console.error(this.verifyUser_errorMessage);
          alert(this.verifyUser_errorMessage);
        }
        this.userVerified = response == true;
      },
      (error)=>{
        console.error(error);
        alert(this.verifyUser_errorMessage);
        this.userVerified = false;
      },
    );
  }
  
  setWish(wish: string): void {
    console.log("Sending wish for user:", this.userId);
    
    let request = {
      userName: this.userId,
      wishContent: wish
    }
    
    this.rest.send("/saveWish", request).subscribe(
      (response: Boolean)=>{
        if (response) {
          console.log(this.setWish_successMessage);
        } else {
          console.error(this.setWish_errorMessage);
          alert(this.setWish_errorMessage);
        }
      },
      (error)=>{
        console.error(error);
        alert(this.setWish_errorMessage);
      },
    ); 
  }

}
