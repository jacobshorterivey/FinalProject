import { FosterService } from './../../services/foster.service';
import { Component, OnInit } from '@angular/core';
import { Foster } from './../../models/foster';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-foster-list',
  templateUrl: './foster-list.component.html',
  styleUrls: ['./foster-list.component.css']
})
export class FosterListComponent implements OnInit {

  // FIELDS
  fosters: Foster[] = [];
  urlId: number;
  selected: Foster;

  // CONSTRUCTOR
  constructor(private svc: FosterService, private currentRoute: ActivatedRoute) { }

  // METHODS
  ngOnInit() {
    this.loadEvents();
  }

  loadEvents() {
    this.svc.index().subscribe(
      (pass) => {
        this.fosters = pass;
      },
      (fail) => {
        console.error(fail);
      }
    );
  }

  openModule(foster: Foster) {
    this.selected = foster;
  }
}
