import { NgForm } from '@angular/forms';
import { EmailService } from './../../services/email.service';
import { Component, OnInit, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShelterService } from 'src/app/services/shelter.service';
import { Shelter } from './../../models/shelter';
import { Pet } from 'src/app/models/pet';
import { AuthService } from 'src/app/services/auth.service';
import { EmailServiceImpl } from 'src/app/models/email';
import { Account } from 'src/app/models/account';

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
  pass: string;

  lat: any;
  long: any;
  location: any;
  mapZoom = 17;

  constructor(private svc: ShelterService, private currentRoute: ActivatedRoute, private auth: AuthService,
              private emailSvc: EmailService, private cdRef: ChangeDetectorRef) { }

  ngOnInit() {
    this.getUserPass();
    this.account = JSON.parse(localStorage.getItem('account'));
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
                this.loadShelterPets(shelter.id);

                // tslint:disable-next-line: max-line-length
                this.svc.findLocation(this.selected.address.street, this.selected.address.city, this.selected.address.stateAbbr).subscribe(loc => {
                  this.lat = loc.results[0].geometry.location.lat;
                  this.long = loc.results[0].geometry.location.lng;
                });

                if (this.account && this.selected) {
                // tslint:disable-next-line: radix
                if (this.selected.account.id === this.account.id) {
                  this.isShelterLoggedIn = true;
                  }
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
    this.editShelter.account.password = this.pass;
    this.svc.update(this.editShelter.id, this.editShelter).subscribe(
      data => {
        this.editShelter = null;
        this.logout();
        this.login(this.selected.account.username, this.selected.account.password);
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

  getUserPass() {
    const a = (atob(this.auth.getCredentials()));  // converts to username:password
    const b = a.split(':'); // splits into an array of [username, password]
    const c = b[1]; // obtains password
    this.pass = c; // sets password for form and update
  }
}
