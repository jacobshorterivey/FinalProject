import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import { Account } from 'src/app/models/account';

@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  users: User[] = [];
  account: Account = new Account();

  constructor(private userSvc: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    this.loadUsers();
  }

  loadUsers() {
    this.userSvc.index().subscribe(
      data => {
        this.account = JSON.parse(localStorage.getItem('account'));
        this.users = data;
      },
      err => {
        console.error(err);
        console.error('TodoListCompenent.loadtodoList():error loading todos');
      }
    );
  }

  disableUser(user: User) {
    if (user.account.active) {
      user.account.active = false;
    } else if (!user.account.active) {
      user.account.active = true;
    }

    this.userSvc.update(user.id, user).subscribe(
      data => {
        this.loadUsers();
      },
      error => {
        console.log('AdminPageComponent(): disableUser(user: User) method: error disabling and updating user list');
        console.log(error);
      }
    );
  }
}
