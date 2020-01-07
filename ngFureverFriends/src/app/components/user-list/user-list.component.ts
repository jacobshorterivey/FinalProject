import { ShelterService } from 'src/app/services/shelter.service';
import { Account } from './../../models/account';
import { EmailService } from './../../services/email.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { EmailServiceImpl } from 'src/app/models/email';
import { NgForm } from '@angular/forms';
import { Shelter } from 'src/app/models/shelter';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrls: ['./user-list.component.css']
})
export class UserListComponent implements OnInit {

  // FIELDS
  users: User[] = [];
  urlId: number;
  selected: User;
  newEmail: EmailServiceImpl;
  volunteers: User[] = [];
  sentEmailMessage: boolean;
  account: Account;
  shelter: Shelter;

  // CONSTRUCTOR
  constructor(private svc: UserService, private currentRoute: ActivatedRoute, private cdRef: ChangeDetectorRef,
              private emailSvc: EmailService, private shelterSvc: ShelterService) { }

  // METHODS
  ngOnInit() {
    this.loadEvents();
    this.account = JSON.parse(localStorage.getItem('account'));

    this.shelterSvc.index().subscribe(
      pass => {
        pass.forEach(e => {
          if (e.account.id === this.account.id) {
            this.shelter = e;
          }
        }
        );
      },
      fail => {}
    );
  }


  loadEvents() {
    this.svc.index().subscribe(
      (pass) => {
        this.users = pass;
        this.volunteers = this.getVols(this.users);
      },
      (fail) => {
        console.error(fail);
      }
    );
  }

  getVols(users: User[]): User[] {
    const vols = [];
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < users.length; i++) {
      if (users[i].skills.length > 0) {
        vols.push(users[i]);
      }
    }
    return vols;
  }

  openModule(vol: User) {
    this.selected = vol;
  }

  sendEmail(email: NgForm) {
    this.newEmail = email.value;
    this.newEmail.toAddress = 'fureverfriendsemail@gmail.com';
    email.value.email = this.shelter.email;
    // tslint:disable-next-line: max-line-length
    this.newEmail.body = 'Please send replies to the following email: <br/>' + email.value.email + '<br/><br/>----------------------<br/>Original Message:<br/>' + email.value.body;
    this.emailSvc.send(this.newEmail).subscribe(
      (pass) => {
        email.reset();
      },
      (fail) => {
        email.reset();
      }
    );
    // TRIGGERS UPDATE MESSAGE
    this.sentEmailMessage = false;
    this.cdRef.detectChanges();
    this.sentEmailMessage = true;
  }
}
