import { catchError, tap } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})

export class AuthService {
  private baseUrl = environment.baseUrl;

  constructor(private http: HttpClient) { }

  login(username, password) {
    console.log(username, password);
    // Make credentials
    const credentials = this.generateBasicAuthCredentials(username, password);
    console.log(credentials);
    // Send credentials as Authorization header (this is spring security convention for basic auth)
    const httpOptions = {
      headers: new HttpHeaders({
        Authorization: `Basic ${credentials}`,
        'X-Requested-With': 'XMLHttpRequest',
        'Content-type': 'application/json'
      })
    };
    console.log(httpOptions);

    // create request to authenticate credentials
    return this.http
      .get(this.baseUrl + 'authenticate', httpOptions)
      .pipe(
        tap((res) => {
          localStorage.setItem('credentials' , credentials);
          console.log(res);
          return res;
        }),
        catchError((err: any) => {
          console.log(err);
          return throwError('AuthService.login(): Error logging in.');
        })
      );
  }

  registerUser(user) {
    // create request to register a new account
    return this.http.post(this.baseUrl + 'api/user/register', user)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  registerShelter(user) {
    // create request to register a new account
    return this.http.post(this.baseUrl + 'api/shelter/register', user)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AuthService.register(): error registering user.');
      })
    );
  }

  logout() {
    localStorage.removeItem('credentials');
  }

  checkLogin() {
    if (localStorage.getItem('credentials')) {
      return true;
    }
    return false;
  }

  generateBasicAuthCredentials(username, password) {
    return btoa(`${username}:${password}`);
  }

  getCredentials() {
    return localStorage.getItem('credentials');
  }
}
