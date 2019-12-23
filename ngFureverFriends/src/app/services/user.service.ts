import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { catchError } from 'rxjs/operators';
import { User } from '../models/user';
import { throwError, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/user';

  constructor(private http: HttpClient, private auth: AuthService) { }

  index() {
    if (!this.auth.checkLogin()) {
      return null;
    }
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.get<User[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('UserService.index(): Error creating index of users.');
      })
    );
  }
  showOne(id: string) {
    const httpOptions = {
      headers: new HttpHeaders({
      })
    };
    return this.http.get<User>(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('User show error');
      })
    );
  }
    show(id): Observable<User> {
    return this.http.get<User>(this.url + '/' + id);
  }

  create(newUser: User) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.post<User>(this.url + '?sorted=true', newUser, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('UserService.create(): Error updating user.');
      })
    );
  }

  // ADD Authorization later
  update(id: number, user: User) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.put(this.url + '/' + id, user, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('UserService.update(): Error updating user');
      })
    );
  }

  destroy(id: number) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.delete<User>(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('UserService.destroy(): Error deleting user in service');
      })
    );
  }
}
