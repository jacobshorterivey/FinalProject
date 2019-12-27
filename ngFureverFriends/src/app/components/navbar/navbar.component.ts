import { Account } from 'src/app/models/account';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';


@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  // FIELDS

  account: Account = new Account();
  blankObj: object = null;
  navbar = true;

  // CONSTRUCTOR
  constructor(private auth: AuthService, router: RouterModule) { }

  ngOnInit() {
  }

  // attempt logging in.  fails.
  login(form: NgForm) {
    console.log('NavbarComponent.login(): ');
    console.log(form.value);
    this.auth.login(form.value.username, form.value.password).subscribe(
      next => {
        const loggedIn = next;
        this.account = loggedIn;
        console.log(next);
        // this.router.navigateByUrl('/user/'`user.id`)
      },
      error => {
        console.log('error logging in.');
        console.log(error);
      }
    );
  }
}
