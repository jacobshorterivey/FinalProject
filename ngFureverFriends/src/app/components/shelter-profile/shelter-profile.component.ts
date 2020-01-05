import { NgForm } from '@angular/forms';
import { EmailService } from './../../services/email.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShelterService } from 'src/app/services/shelter.service';
import { Shelter } from './../../models/shelter';
import { Pet } from 'src/app/models/pet';
import { AuthService } from 'src/app/services/auth.service';
import { EmailServiceImpl } from 'src/app/models/email';

@Component({
  selector: 'app-shelter-profile',
  templateUrl: './shelter-profile.component.html',
  styleUrls: ['./shelter-profile.component.css']
})
export class ShelterProfileComponent implements OnInit {
  // FIELDS
  account: Account;
  shelters: Shelter[] = [];
  urlId: number;
  selected: Shelter;
  profilePic: string;
  newEmail: EmailServiceImpl;
  userEmail: string;
  sentEmailMessage: boolean;
  pets: Pet[] = [];
  selectedPet: Pet;
  currentShelter: Shelter;
  isShelterLoggedIn: boolean;
  isAnyoneLoggedIn: boolean;
  editShelter: Shelter;

  constructor(private svc: ShelterService, private currentRoute: ActivatedRoute, private auth: AuthService,
              private emailSvc: EmailService, private cdRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.account = JSON.parse(localStorage.getItem('account'));
    console.log(this.account);
    this.checkIfLoggedIn();
    this.scrollToTops();
    this.loadEvents();

    if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
      this.svc.index().subscribe(
        data => {
          this.shelters = data;
          if (this.currentRoute.snapshot.paramMap.get('id')) {
            this.urlId = +this.currentRoute.snapshot.paramMap.get('id');
            this.shelters.forEach((shelter) => {
              if (shelter.id === this.urlId) {
                this.selected = shelter;
                console.log('Selected info: ' + this.selected.account.id);
                this.loadShelterPets(shelter.id);
                // tslint:disable-next-line: radix
                if (this.selected.account.id === parseInt(this.account.id)) {
                  console.log('account info: ' + this.account);
                  this.isShelterLoggedIn = true;
                }
              }
            });
          }
        },
        err => console.error('Reload error in Component')
      );

    }
  }
  updateShelter() {
    this.svc.update(this.selected.id, this.editShelter).subscribe(
      data => {
        console.log(data);
        this.editShelter = null;
        this.logout();
        this.login(this.selected.account.username, this.selected.account.password);
        console.log(this.selected.id + ' ' + this.selected.name);
        // this.reload();
      },
      err => {
        console.error(err);
      }
    );
    // this.reload();
  }
  logout() {
    this.auth.logout();
  }

  login(username, password) {
    this.auth.login(username, password).subscribe(
      next => {

      },
      error => {
        console.error('error logging in.');
      }
    );
  }

  loadEvents() {
    this.svc.index().subscribe(
      (pass) => {
        this.shelters = pass;
      },
      (fail) => {
        console.error(fail);
      }
    );
  }

  loadShelterPets(id: number) {
    this.svc.findPets(id).subscribe(
      (pass) => {
        this.pets = pass;
      },
      (fail) => {
        console.error(fail);
      }
    );
  }

  openModule(animal: Pet) {
    this.selectedPet = animal;
  }

  scrollToTops() {
    const element = document.querySelector('.bodyComponent');
    element.scrollIntoView({ behavior: 'smooth' });
  }

  toggleLogin() {
    const login = document.querySelector('.linktoggle');
  }

  checkIfLoggedIn() {
    this.isAnyoneLoggedIn = this.auth.checkLogin();
  }

  sendEmail(email: NgForm) {
    this.newEmail = email.value;
    this.newEmail.toAddress = 'fureverfriendsemail@gmail.com';
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
