import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';
import { User } from 'src/app/models/user';
import { Shelter } from 'src/app/models/shelter';
import { Account } from 'src/app/models/account';
import { Address } from 'src/app/models/address';
import { ShelterService } from 'src/app/services/shelter.service';
import { UserService } from 'src/app/services/user.service';
import { SequenceEqualSubscriber } from 'rxjs/internal/operators/sequenceEqual';
import { NavbarComponent } from '../navbar/navbar.component';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  users: User[] = [];
  shelters: Shelter[] = [];
  newUser: User = new User();
  newShelter: Shelter = new Shelter();
  userBool = false;
  shelterBool = false;
  user: User;
  shelter: Shelter;

  constructor(private router: Router, private auth: AuthService,
              private userSvc: UserService,
              private shelterSvc: ShelterService,
              private navBar: NavbarComponent) { }

  ngOnInit() {
    this.auth.login('testUser', 'test').subscribe(
      next => {
      },
      error => {
      }
    );
    this.newUser.account = new Account();
    this.newUser.address = new Address();
    this.newShelter.account = new Account();
    this.newShelter.address = new Address();
  }

  loadAll() {
    this.loadUsers();
    this.loadShelters();
  }

  loadUsers() {
    this.userSvc.index().subscribe(
      data => {
        this.users = data;
      },
      error => {
        console.log('RegisterComponent.loadUsers(): Error loading and indexing all users');
        console.log(error);
      }
    );
  }

  loadShelters() {
    this.shelterSvc.index().subscribe(
      data => {
        this.shelters = data;
      },
      error => {
        console.log('RegisterComponent.loadShelters(): Error loading and indexing all shelters');
        console.log(error);
      }
    );
  }

  registerUser() {
    this.auth.logout();
    this.auth.registerUser(this.newUser).subscribe(
      data => {
        this.auth.login(this.newUser.account.username, this.newUser.account.password).subscribe(
          next => {
            this.routeToProfile();
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
        this.auth.logout();
        this.auth.registerShelter(this.newShelter).subscribe(
          data => {
            this.auth.login(this.newShelter.account.username, this.newShelter.account.password).subscribe(
              next => {
                this.routeToProfile();
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

  routeToProfile() {
      if (this.newUser.account.username !== null && this.newUser.account.username !== undefined) {
        this.userSvc.index().subscribe(
          data => {
            this.users = data;
          },
          error => {
            console.log('RegisterComponent.routeToProfile(): Error loading and indexing all users');
            console.log(error);
          }
          );
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < this.users.length; i++) {
          const user = this.users[i];
          if (user.account.username === this.newUser.account.username) {
            const id = user.id;
            this.navBar.user = user;
            localStorage.setItem('account', JSON.stringify(user.account));
            this.router.navigateByUrl('/user/' + id);
          }
        }
      }

      if (this.newShelter.account.username !== null && this.newShelter.account.username !== undefined) {
        this.shelterSvc.index().subscribe(
          data => {
            this.shelters = data;
          },
          error => {
            console.log('RegisterComponent.routeToProfile(): Error loading and indexing all shelters');
            console.log(error);
          }
          );
          // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < this.shelters.length; i++) {
            const shelter = this.shelters[i];
            if (shelter.account.username === this.newShelter.account.username) {
              const id = shelter.id;
              this.navBar.shelter = shelter;
              localStorage.setItem('account', JSON.stringify(shelter.account));
              this.router.navigateByUrl('/shelter/' + id);
            }
        }
      }
  }
}
