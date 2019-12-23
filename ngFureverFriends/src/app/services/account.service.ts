import { AuthService } from './auth.service';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Account } from '../models/account';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  // baseUrl = 'http://localhost:8087/';
  baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/account';
  accounts: Account[] = [];

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
    return this.http.get<Account[]>(this.url).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AccountService.index(): Error retrieving accounts');
      }
      ));
  }

  show(id: number) {
    if (!this.auth.checkLogin()) {
      return null;
    }
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.get<Account[]>(this.url + '/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AccountService.show(): Error retrieving accounts');
      }
      ));
  }

  create(account: Account) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.post<Account>(this.url, account, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AccountService.create(): Error creating account');
      })
    );
  }

  update(id: number, account: Account) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.put<Account>(this.url + '/' + id, account, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AccountService.update(): Error updating account');
      })
    );
  }

  destroy(id: number) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.delete<Account>(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('AccountService.destroy(): Error deleting account in service');
      })
    );
  }

}
