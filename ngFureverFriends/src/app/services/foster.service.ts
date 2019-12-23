import { catchError } from 'rxjs/operators';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { Foster } from '../models/foster';

@Injectable({
  providedIn: 'root'
})
export class FosterService {
  baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/foster';

  constructor(private http: HttpClient) { }

  // TRY TO ADD AUTH SO ONLY SHELTERS CAN UTILIZE THIS SEARCH.
  index() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Foster[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('FosterService.index(): Error creating index of fosters.');
      })
    );
  }

  // TRY TO ADD AUTH SO ONLY USERS CAN CREATE ONCE.
  create(newFoster: Foster) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };
    return this.http.post<Foster>(this.url + '?sorted=true', newFoster, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('FosterService.create(): Error creating foster.');
      })
    );
  }

  update(foster: Foster) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json',
      }
    };
    return this.http.put(`{this.url}/${foster.id}`, foster, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('FosterService.update(): Error updating foster');
      })
    );
  }

}
