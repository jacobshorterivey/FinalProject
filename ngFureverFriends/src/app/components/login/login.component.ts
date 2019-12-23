import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { Shelter } from 'src/app/models/shelter';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { Account } from 'src/app/models/account';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogin: User = new User();
  userBool = false;
  shelterLogin: Shelter = new Shelter();
  shelterBool = false;

  constructor(private router: Router, private auth: AuthService) { }

  ngOnInit() {
    this.userLogin.account = new Account();
    this.shelterLogin.account = new Account();
  }

  loginUser() {
    this.auth.login(this.userLogin.account.username, this.userLogin.account.password).subscribe(
      data => {
        console.log('LoginComponent.loginUser(): User logged in');
        this.router.navigateByUrl('/home');
      },
      fail => {
        console.error('LoginCOmponent.loginUser(): Error logging in user');
        console.error(fail);
      }
    );
  }

  loginShelter() {
    this.auth.login(this.shelterLogin.account.username, this.shelterLogin.account.password).subscribe(
      data => {
        console.log('LoginComponent.shelterLogin(): Shelter Logged in');
        this.router.navigateByUrl('/home');
      },
      fail => {
        console.error('LoginComponent.shelterLogin(): Error logging in shelter');
        console.error(fail);
      }
    );
  }
}
