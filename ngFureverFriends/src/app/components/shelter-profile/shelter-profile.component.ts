import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShelterService } from 'src/app/services/shelter.service';
import { Shelter } from './../../models/shelter';
import { Pet } from 'src/app/models/pet';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-shelter-profile',
  templateUrl: './shelter-profile.component.html',
  styleUrls: ['./shelter-profile.component.css']
})
export class ShelterProfileComponent implements OnInit {
  // FIELDS
  shelters: Shelter[] = [];
  urlId: number;
  selected: Shelter;
  profilePic: string;
  pets: Pet[] = [];
  selectedPet: Pet;
  isUserLoggedIn: boolean;

  constructor(private svc: ShelterService, private currentRoute: ActivatedRoute, private auth: AuthService) { }

  ngOnInit() {
    this.scrollToTops();
    this.loadEvents();
    if (this.auth.getCredentials()) {
      this.checkLogin();
      }

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
              }
            });
          }
        },
        err => console.error('Reload error in Component')
      );
    }
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
  checkLogin() {
    if (this.auth.checkLogin() === true) {
      // if (this.auth.getCredentials === this.selected.account.username) {
      // this.isUserLoggedIn = true;
      // }
    }
  }


}
