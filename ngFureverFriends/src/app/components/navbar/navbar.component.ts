import { subscribeOn } from 'rxjs/operators';
import { Account } from 'src/app/models/account';
import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { NgForm } from '@angular/forms';
import { Router, RouterModule, ActivatedRoute } from '@angular/router';
import { UserService } from 'src/app/services/user.service';


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
  isUserLoggedIn: boolean;
  errorMessage: boolean;
  user: User;

  // CONSTRUCTOR
  constructor(private auth: AuthService, private router: RouterModule,
              private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit() {
    this.checkLogin();
  }

  // attempt logging in.  fails.
  login(form: NgForm) {
    console.log('NavbarComponent.login(): ');
    this.auth.login(form.value.username, form.value.password).subscribe(
      next => {
        this.isUserLoggedIn = true;
        window.location.reload(); // remove when routing is added.
        // this.router.navigateByUrl('/user/'`user.id`)
      },
      error => {
        console.log('error logging in.');
        console.log(error);
        this.errorMessage = true;
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
      if (!this.user) {
        this.userService.showOne('1').subscribe(
          data => {
            this.user = data;

            if (this.user === null) {
              // this.router.navigateByUrl('user' + this.route.snapshot.paramMap.get('id') + 'NotFound');
            }
          },
          err => console.error('failed to find user')
        );
      }
  }


}
