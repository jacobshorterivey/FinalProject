import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import { Account } from 'src/app/models/account';
import { Shelter } from 'src/app/models/shelter';
import { ShelterService } from 'src/app/services/shelter.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  users: User[] = [];
  shelters: Shelter[] = [];
  account: Account = new Account();
  userList = false;
  shelterList = false;
  pass = '';

  constructor(private userSvc: UserService,
              private shelterSvc: ShelterService,
              private authSvc: AuthService,
              private route: ActivatedRoute,
              private router: Router) { }

  ngOnInit() {
    this.loadUsers();
    this.loadShelters();
  }

  loadEverything() {
    this.loadShelters();
    this.loadUsers();
  }

  loadShelters() {
    this.shelterSvc.index().subscribe(
      data => {
        this.account = JSON.parse(localStorage.getItem('account'));
        this.shelters = data;
      },
      err => {
        console.error('AdminPageComponent.loadShelters(): error loading in shelters');
        console.error(err);
      }
    );
  }

  loadUsers() {
    this.userSvc.index().subscribe(
      data => {
        this.account = JSON.parse(localStorage.getItem('account'));
        this.users = data;
      },
      err => {
        console.error('AdminPageComponent.loadUsers(): error loading in users');
        console.error(err);
      }
    );
  }

  disableUser(user: User) {
    if (user.account.active) {
      user.account.active = false;
    } else if (!user.account.active) {
      user.account.active = true;
    }

    this.userSvc.update(user.id, user).subscribe(
      data => {
        this.loadUsers();
      },
      err => {
        console.error('AdminPageComponent.disableUser(user: User): error disabling/enabling and updating user');
        console.error(err);
      }
    );
  }

  disableShelter(shelter: Shelter) {
    if (shelter.account.active) {
      shelter.account.active = false;
    } else if (!shelter.account.active) {
      shelter.account.active = true;
    }

    this.shelterSvc.update(shelter.id, shelter).subscribe(
      data => {
        this.loadShelters();
      },
      err => {
        console.log(shelter);
        console.error('AdminPageComonent.disableShelter(shelter: Shelter) error disabling/enabling and updating shelter');
        console.error(err);
      }
    );
  }
}
