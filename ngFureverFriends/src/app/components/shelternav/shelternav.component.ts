import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-shelternav',
  templateUrl: './shelternav.component.html',
  styleUrls: ['./shelternav.component.css']
})
export class ShelternavComponent implements OnInit {

  isUserLoggedIn: boolean;

  constructor(private auth: AuthService) { }

  ngOnInit() {
    if (this.auth.getCredentials()) {
    this.checkLogin();
    }
  }

  checkLogin() {
    if (this.auth.checkLogin() === true) {
      this.isUserLoggedIn = true;
    }
  }

}
