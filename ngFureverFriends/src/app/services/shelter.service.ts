import { catchError } from 'rxjs/operators';
import { Shelter } from './../models/shelter';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ShelterService {
  baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/shelter';

  constructor(private http: HttpClient) { }

  index() {
    return this.http.get<Shelter[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('ShelterService.index(): Error creating index of shelters.');
      })
    );
  }

  create(newShelter: Shelter) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type':  'application/json',
      })
    };
    return this.http.post<Shelter>(this.url + '?sorted=true', newShelter, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('ShelterService.create(): Error updating shelter.');
      })
    );
  }

  // ADD Authorization later
  update(shelter: Shelter) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json',
      }
    };
    return this.http.put(`{this.url}/${shelter.id}`, shelter, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('ShelterService.update(): Error updating shelter');
      })
    );
  }
}
