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
  selected: Shelter = null;

  constructor(private svc: ShelterService, private currentRoute: ActivatedRoute) { }

  // The following code will first load the '/shelter' route with a list then determine which shelter to use.
  //  This requires buffer time while it dermines which shelter to populate.  For now, shelter id is linked
  // through the shelter-list page.

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
}

