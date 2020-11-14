import { Component, OnInit } from '@angular/core';
import { RestServiceComponent, RestResponse } from '../rest-service/rest-service.component';

@Component({
  selector: 'app-koncano',
  templateUrl: './koncano.component.html',
  styleUrls: ['./koncano.component.scss']
})
export class KoncanoComponent implements OnInit {

  private readonly badFetch_errorMessage: string = "Neuspešno branje uporabnikov!";
  private readonly wishSend_errorMessage: string = "Želje neuspešno oddane!";
  private readonly wishSend_successMessage: string = "Želje uspešno oddane!";
  private readonly inviteSend_errorMessage: string = "Vabila neuspešno oddana!";
  private readonly inviteSend_successMessage: string = "Vabila uspešno oddana!";

  allUsers: string[] = [];
  wishesGiven: string[] = [];
  userWishesGiven: UserWishGiven[] = [];
  done: boolean = false;
  
  constructor(private rest: RestServiceComponent) { }

  ngOnInit() {
    this.fetchUserAndWishData();
  }

  fetchUserAndWishData(): void {
    this.rest.fetch<string[]>("/users", null).subscribe(
      (response: string[])=>{
        console.log("Retrieved users: ", response);
        this.allUsers = response;
        this.calculateUserWishes();
      },
      (error)=>{
        console.error(error);
        alert(this.badFetch_errorMessage);
      },
    ); 
    this.rest.fetch<string[]>("/wishList", null).subscribe(
      (response: string[])=>{
        console.log("Retrieved wishes: ", response);
          this.wishesGiven = response;
          this.calculateUserWishes();
      },
      (error)=>{
        console.error(error);
        alert(this.badFetch_errorMessage);
      },
    ); 
  }
  
  calculateUserWishes() {
    if (this.allUsers.length == 0 || this.wishesGiven.length == 0) {
      console.log("Not enough data!");
      return;
    }
    
    let usersDone: number = 0;
    for (let user of this.allUsers) {
      let userwishData: UserWishGiven = new UserWishGiven(user);
      if (this.wishesGiven.indexOf(user) > -1) {
        userwishData.isWishMade = true;
        usersDone = usersDone + 1;
      }
      this.userWishesGiven.push(userwishData);
    }
    
    this.done = (usersDone == this.allUsers.length);
  }

  sendWishes() {
    this.rest.send("/distributeWishes", null).subscribe(
      (response: boolean)=>{
        if (response) {
          console.log(this.wishSend_successMessage);
          alert(this.wishSend_successMessage);
        } else {
          console.error(this.wishSend_errorMessage);
          alert(this.wishSend_errorMessage);
        }
      },
      (error)=>{
        console.error(error);
        alert(this.wishSend_errorMessage);
      },
    ); 
  }
  
  sendInvitations() {
    this.rest.send("/distributeInvitations", null).subscribe(
      (response: boolean)=>{
        if (response) {
          console.log(this.inviteSend_successMessage);
          alert(this.inviteSend_successMessage);
        } else {
          console.error(this.inviteSend_errorMessage);
          alert(this.inviteSend_errorMessage);
        }
      },
      (error)=>{
        console.error(error);
        alert(this.inviteSend_errorMessage);
      },
    ); 
  }

}

  
class UserWishGiven {
  
  constructor(user: string) {this.user = user; this.isWishMade = false;}
  
  user: string;
  isWishMade: boolean;
}
