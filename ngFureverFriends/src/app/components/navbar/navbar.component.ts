import { Account } from 'src/app/models/account';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private auth: AuthService, router: RouterModule) { }

  navbar = true;
  acc: Account;

  ngOnInit() {
  }

  // attempt loiggin in.  fails.
  login(form: Account) {
    this.auth.login(form.username, form.password).subscribe(
      next => {
        console.log('Logged in ');
        // this.router.navigateByUrl('/todo');
      },
      error => {
        console.error('error logging in.');
      }
    );
  }
}
