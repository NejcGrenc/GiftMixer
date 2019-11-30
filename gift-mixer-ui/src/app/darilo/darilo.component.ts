import { Component, OnInit, Input } from '@angular/core';
import { RestServiceComponent, RestResponse } from '../rest-service/rest-service.component';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-darilo',
  templateUrl: './darilo.component.html',
  styleUrls: ['./darilo.component.scss']
})
export class DariloComponent implements OnInit {
  
  private readonly verifyUser_successMessage: string = "Uporabnik prepoznan!";
  private readonly verifyUser_errorMessage: string = "Neznan uporabnik. Ali pa je bila vnešena nepravlina varnostna koda.";
  private readonly setWish_successMessage: string = "Želje so uspešno shranjene!";
  private readonly setWish_errorMessage: string = "Zgodila se je nepričakovana napaka. \nLahko se zgodi, da se podatki izgubijo, zato raje napravi rezervno kopijo!";
  private readonly getWish_successMessage: string = "Želje so uspešno prebrane!";
  private readonly getWish_errorMessage: string = "Želja ni bilo mogoče najti!";

  userName: string;
  private userCode: string;
  userVerified: boolean;
  
  wish: string;

  constructor(private rest: RestServiceComponent, private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let userIdCode = params['userIdCode'];
      this.verifyUser(userIdCode);
    });
  }
  
  private verifyUser(userIdCode: string): void {
    this.userName = userIdCode.split('-')[0];
    this.userCode = userIdCode.split('-')[1];
    console.log("Verificating user:", this.userName, this.userCode);
    
    this.rest.send("/verifyUser", {userName: this.userName, userCode: this.userCode}).subscribe(
      (response: boolean)=>{
        if (response) {
          console.log(this.verifyUser_successMessage, this.userName);
          this.validUser();
        } else {
          this.userVerified = false;
          console.error(this.verifyUser_errorMessage);
          alert(this.verifyUser_errorMessage);   
        }
      },
      (error)=>{
        this.userVerified = false;
        console.error(error);
        alert(this.verifyUser_errorMessage);
      },
    );
  }
  private validUser(): void {
    this.userVerified = true;
    this.getWish();
  }
  
  setWish(wish: string): void {
    console.log("Sending wish for user:", this.userName);
    
    let request = {
      userName: this.userName,
      wishContent: this.wish
    }
    
    this.rest.send("/saveWish", request).subscribe(
      (response: boolean)=>{
        if (response) {
          console.log(this.setWish_successMessage);
          alert(this.setWish_successMessage);
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
  
  getWish(): void {
    console.log("Getting wish for user:", this.userName);

    this.rest.fetch<string>("/fetchWish", this.userName).subscribe(
      (response: RestResponse<string>)=>{
        if (response.success) {
          console.log(this.getWish_successMessage);
          this.wish = response.value;
        } else {
          console.error(this.getWish_errorMessage);
        }
      },
      (error)=>{
        console.error(error);
      },
    ); 
  }

}
