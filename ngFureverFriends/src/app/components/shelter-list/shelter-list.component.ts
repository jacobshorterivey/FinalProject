import { ShelterService } from './../../services/shelter.service';
import { Component, OnInit } from '@angular/core';
import { Shelter } from './../../models/shelter';

@Component({
  selector: 'app-shelter-list',
  templateUrl: './shelter-list.component.html',
  styleUrls: ['./shelter-list.component.css']
})
export class ShelterListComponent implements OnInit {

  // FIELDS
  shelters: Shelter[] = [];

  // CONSTRUCTOR
  constructor(private svc: ShelterService) { }

  // METHODS
  ngOnInit() {
    this.loadEvents();
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
