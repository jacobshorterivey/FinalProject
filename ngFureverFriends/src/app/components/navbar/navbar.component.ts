import { AuthService } from 'src/app/services/auth.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private auth: AuthService, router: Router) { }

  navbar = true;

  ngOnInit() {
  }

  login(username: string, password: string) {
    this.auth.login(username, password).subscribe(
      next => {
        console.log('RegisterComponent.register(): user logged in, routing to /todo.');
        this.router.navigateByUrl('/todo');
      },
      error => {
        console.error('RegisterComponent.register(): error logging in.');
      }
    );
  }
}
