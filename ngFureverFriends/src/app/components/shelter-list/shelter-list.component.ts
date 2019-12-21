import { ShelterService } from './../../services/shelter.service';
import { Component, OnInit } from '@angular/core';
import { Shelter } from './../../models/shelter';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-shelter-list',
  templateUrl: './shelter-list.component.html',
  styleUrls: ['./shelter-list.component.css']
})
export class ShelterListComponent implements OnInit {

  // FIELDS
  shelters: Shelter[] = [];
  urlId: number;
  selected: Shelter;

  // CONSTRUCTOR
  constructor(private svc: ShelterService, private currentRoute: ActivatedRoute) { }

  // METHODS
  ngOnInit() {
    this.loadEvents();

    // if (!this.selected && this.currentRoute.snapshot.paramMap.get('id')) {
    //   this.svc.index().subscribe(
    //     data => {
    //       this.shelters = data;
    //       if (this.currentRoute.snapshot.paramMap.get('id')) {
    //         this.urlId = +this.currentRoute.snapshot.paramMap.get('id');
    //         this.shelters.forEach((shelter) => {
    //           if (shelter.id === this.urlId) {
    //           this.selected = shelter;
    //           }
    //         });
    //       }
    //     },
    //     err => console.error('Reload error in Component')
    //   );
    // }
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

  // displayShelter(shelter) {
  //   this.selected = shelter;
  // }

}
