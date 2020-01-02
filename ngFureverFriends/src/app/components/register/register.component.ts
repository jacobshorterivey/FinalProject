import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { Shelter } from 'src/app/models/shelter';
import { Account } from 'src/app/models/account';
import { Address } from 'src/app/models/address';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  newUser: User = new User();
  newShelter: Shelter = new Shelter();
  userBool = false;
  shelterBool = false;

  constructor(private router: Router, private auth: AuthService) { }

  ngOnInit() {
    this.newUser.account = new Account();
    this.newUser.address = new Address();
    this.newShelter.account = new Account();
    this.newShelter.address = new Address();
  }

  registerUser() {
    console.log(this.newUser);
    this.auth.registerUser(this.newUser).subscribe(
      data => {
        console.log('RegisterComponent.registerUser(): user registered.');
        this.auth.login(this.newUser.account.username, this.newUser.account.password).subscribe(
          next => {
            console.log('RegisterComponent.registerUser(): user logged in, routing to /home.');
            this.router.navigateByUrl('/home');
          },
          error => {
            console.error('RegisterComponent.registerUser(): error logging in.');
          }
        );
      },
      err => {
        console.error('RegisterComponent.registerUser(): error registering.');
        console.error(err);
      }
    );
  }

  registerShelter() {
    this.auth.registerShelter(this.newShelter).subscribe(
      data => {
        console.log('RegisterComponent.registerShelter(): shelter registered.');
        this.auth.login(this.newShelter.account.username, this.newShelter.account.password).subscribe(
          next => {
            console.log('RegisterComponent.registerShelter(): shelter logged in, routing to /home.');
            this.router.navigateByUrl('/home');
          },
          error => {
            console.error('RegisterComponent.registerShelter(): error logging in.');
          }
        );
      },
      err => {
        console.error('RegisterComponent.registerShelter(): error registering.');
        console.error(err);
      }
    );
  }
}
