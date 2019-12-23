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
  user = User;
  constructor(private userService: UserService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit() {
  }

  // getUser() {
  //   this.userService.index().subscribe(
  //     data => {
  //       this.user = data;
  //     },
  //     err => {
  //       console.error(err);
  //     }
  //   );
  // }
}
