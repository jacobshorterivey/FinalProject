import { AccountService } from './../../services/account.service';
import { UserService } from './../../services/user.service';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from 'src/app/models/user';

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
  constructor(private userService: UserService, private accountService: AccountService,
              private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
    if (!this.user && this.route.snapshot.paramMap.get('id')) {
      this.userService.showOne(this.route.snapshot.paramMap.get('id')).subscribe(
        data => {
          this.user = data;
          console.log('UserProfileComponent.ngOnInit(): user:');
          console.log(this.user);
          if (this.user === null) {
            this.router.navigateByUrl('user' + this.route.snapshot.paramMap.get('id') + 'NotFound');
          }
        },
        err => console.error('failed to find user')
      );
    }
    // this.reload();
  }

  // reload() {
  //   this.userService.showOne(this.route.snapshot.paramMap.get('id')).subscribe(
  //     (aGoodThingHappened) => {
  //       console.log(aGoodThingHappened);
  //       this.user = aGoodThingHappened;
  //     },

  //     (didntWork) => {
  //       console.log(didntWork);
  //     }
  //   );
  // }
}
