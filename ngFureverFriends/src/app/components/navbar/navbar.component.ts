import { subscribeOn } from 'rxjs/operators';
import { Account } from 'src/app/models/account';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';
import { AccountService } from 'src/app/services/account.service';
import { Shelter } from 'src/app/models/shelter';
import { ShelterService } from 'src/app/services/shelter.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {
  // FIELDS

  blankObj: object = null;
  navbar = true;
  isUserLoggedIn: boolean;
  errorMessage: boolean;
  user: User;
  shelter: Shelter;
  account: Account;
  logUsername: string;

  // CONSTRUCTOR
  constructor(
    private auth: AuthService,
    private router: RouterModule,
    private route: ActivatedRoute,
    private userService: UserService,
    private accountService: AccountService,
    private shelterService: ShelterService
  ) {}

  ngOnInit() {
    if (this.auth.checkLogin() === true) {
      this.isUserLoggedIn = true;
    }
    this.account = JSON.parse(localStorage.getItem('account'));
    this.getUser();
  }

  login(form: NgForm) {
    console.log('NavbarComponent.login(): ');
    this.logUsername = form.value.username;
    this.auth.login(form.value.username, form.value.password).subscribe(
      next => {
        this.isUserLoggedIn = true;
        // this.router.navigateByUrl('/user/'`user.id`)
      },
      error => {
        console.log('error logging in.');
        console.log(error);
        this.errorMessage = true;
      }
      );
    this.accountService.index(form.value.username, form.value.password).subscribe(
      ret => {
        const accounts = ret;
        // tslint:disable-next-line: prefer-for-of
        for (let i = 0; i < accounts.length; i++) {
          if (accounts[i].username === this.logUsername) {
            this.account = accounts[i];
            localStorage.setItem('account', JSON.stringify(this.account));
            this.getUser();
          }
        }
      },
      err => {
        console.log('error');
        console.log(err);
      }
    );
  }

  checkLogin() {
    if (this.auth.checkLogin() === true) {
      this.isUserLoggedIn = true;
    }
    this.getUser();
  }

  logout() {
    this.auth.logout();
    this.isUserLoggedIn = false;
    window.location.reload();
  }

  getUser() {
    // this.userService.index().subscribe(
    //   (pass) => {
    //     pass.forEach(element => {
    //       if (element) {
    //         this.user = element;
    //       }
    //     });
    //   },
    //   (fail) => {console.error(fail);
    //   });
    if (!this.user || !this.shelter) {
      if (this.account.role === 'user') {
        this.userService.index().subscribe(
          data => {
            const users = data;
            // tslint:disable-next-line: prefer-for-of
            for (let i = 0; i < users.length; i++) {
              if (users[i].account.id === this.account.id) {
                this.user = users[i];
              }
            }
          },
          error => {
            console.log(
              'Navbar component: getUser() method. Error encountered finding user'
            );
            console.log(error);
          }
        );
      } else if (this.account.role === 'shelter') {
        this.shelterService.index().subscribe(
          data => {
            const shelters = data;
            // tslint:disable-next-line: prefer-for-of
            for (let i = 0; i < shelters.length; i++) {
              if (shelters[i].account.id === this.account.id) {
                this.shelter = shelters[i];
              }
            }
          },
          error => {
            console.log(
              'Navbar component: getUser() method. Error encountered finding shelter'
            );
            console.log(error);
          }
        );
      }
      //   this.userService.showOne(this.account.user).subscribe(
      //     data => {
      //       this.user = data;

      //       if (this.user === null) {
      //         // this.router.navigateByUrl('user' + this.route.snapshot.paramMap.get('id') + 'NotFound');
      //       }
      //     },
      //     err => console.error('failed to find user')
      //   );
    }
  }
}
