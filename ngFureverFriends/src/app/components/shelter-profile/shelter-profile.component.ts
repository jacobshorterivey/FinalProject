import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShelterService } from 'src/app/services/shelter.service';
import { Shelter } from './../../models/shelter';

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

  constructor(private svc: ShelterService, private currentRoute: ActivatedRoute) { }

  ngOnInit() {
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
              this.loadProfilePic(shelter);
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

  loadProfilePic(selected: Shelter) {
    // window.onload = (func) => {
    //   const profPic = document.querySelector('#what');
    //   return this.profilePic = selected.images[0].imageUrl;
      // profPic.setAttribute('style', 'background-image: url("urlPath")');
      // console.log(urlPath);
    // };
  }
}
