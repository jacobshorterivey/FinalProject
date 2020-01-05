import { AuthService } from 'src/app/services/auth.service';
import { Foster } from './../../models/foster';
import { FosterService } from './../../services/foster.service';
import { AccountService } from './../../services/account.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';
import { Shelter } from 'src/app/models/shelter';
import { Account } from 'src/app/models/account';
import { PassThrough } from 'stream';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  // Field
  user: User;
  admin: User;
  selectedUser: User;
  users: User[];
  shelter: Shelter;
  showDetails: boolean;
  account: Account;
  foster: Foster;
  pass: string;
  form: FormGroup;

  constructor(private userService: UserService, private accountService: AccountService,
              private route: ActivatedRoute, private router: Router,
              private fostersvc: FosterService, private authSVC: AuthService) { }

  ngOnInit() {
    this.getUserPass();

    if (!this.user && this.route.snapshot.paramMap.get('id')) {
      this.userService.showOne(this.route.snapshot.paramMap.get('id')).subscribe(
        data => {
          this.user = data;
          console.log('UserProfileComponent.ngOnInit(): user:');
          console.log(this.user);
          if (this.user === null) {
            this.router.navigateByUrl('user' + this.route.snapshot.paramMap.get('id') + 'NotFound');
          }

          // checks if user is shelter or not
          this.account = JSON.parse(localStorage.getItem('account'));
          if (this.account.role === 'shelter') {
            this.showDetails = true;
          }

          // Checks if a user is a foster.  More efficent to create backend search by id.  Maybe in the future.
          this.fostersvc.index().subscribe(
            (pass) => {
              this.account = JSON.parse(localStorage.getItem('account'));
              pass.forEach(element => {
                if (element.user.id === this.user.id) {
                  this.foster = element;
                }
              });
            },
            err => {
              console.error(err);
              console.error('TodoListCompenent.loadtodoList():error loading todos');
            }
          );
        },
        err => console.error('failed to find user')
      );
    }
  }

  updateFoster(fosterUpdate: Foster) {
    this.fostersvc.update(fosterUpdate).subscribe(
      data => { },
      error => {
        console.error('error updating foster');
      }
    );
  }

  // updateVolunteer(user) {
  //   this.userService.update(this.user.id, this.user).subscribe(
  //     data => {
  //       console.log(data);
  //       this.logout();
  //       this.login(this.user.account.username, this.user.account.password);
  //       console.log('success Loggin in!');
  //     },
  //     error => {
  //       console.error('error updating volunteer');
  //       console.log(error);
  //     }
  //   );
  // }

  updateUser(form) {
    // console.log(form.value.username);
    // console.log(form.value[0]);
    this.user.account.password = this.pass;
    console.log(this.pass);
    console.log(this.user.account.password);
    this.userService.update(this.user.id, this.user).subscribe(
      data => {
        console.log(data);
        // this.user.account.password = form.password;
        // this.user.account.username = form.username;
        this.logout();
        this.login(this.user.account.username, this.user.account.password);
        console.log('success Loggin in!');
       },
      error => {
        console.error('error updating user');
      }
    );
  }

  getUserPass() {
    let a = (atob(this.authSVC.getCredentials()));
    let b = a.split(':');
    let c = b[1];
    this.pass = c;
  }

  logout() {
    this.authSVC.logout();
  }

  login(username, password) {
    this.authSVC.login(username, password).subscribe(
      next => {
        console.log('logged in');
      },
      error => {
        console.log('error logging in.');
        console.log(error);
      }
    );
  }

  updateVolunteer() {
    this.user.account.password = this.pass;
    this.userService.update(this.user.id, this.user).subscribe(
      data => {
        console.log(data);
        this.logout();
        this.login(this.user.account.username, this.user.account.password);
        console.log('success Loggin in!');
      },
      error => {
        console.error('error updating volunteer');
        console.log(error);
      }
    );
  }


}
