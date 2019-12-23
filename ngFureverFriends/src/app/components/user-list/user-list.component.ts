import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';

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
  volunteers: User[] = [];

  // CONSTRUCTOR
  constructor(private svc: UserService, private currentRoute: ActivatedRoute) { }

  // METHODS
  ngOnInit() {
    this.loadEvents();
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
}
