import { ShelterService } from 'src/app/services/shelter.service';
import { catchError } from 'rxjs/operators';
import { Shelter } from './../models/shelter';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { throwError } from 'rxjs';
import { AuthService } from './auth.service';
import { Pet } from '../models/pet';


@Injectable({
  providedIn: 'root'
})
export class ShelterService {
  baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/shelter';

  constructor(private http: HttpClient, private auth: AuthService) { }

  index() {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.get<Shelter[]>(this.url + '?sorted=true').pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('ShelterService.index(): Error creating index of shelters.');
      })
    );
  }

  create(newShelter: Shelter) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.post<Shelter>(this.url + '?sorted=true', newShelter, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('ShelterService.create(): Error updating shelter.');
      })
    );
  }

  // ADD Authorization later
  update(id: number, shelter: Shelter) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.put(`{this.url}/${shelter.id}`, shelter, httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('ShelterService.update(): Error updating shelter');
      })
    );
  }

  destroy(id: number) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.delete<Shelter>(this.url + '/' + id, httpOptions).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ShelterService.destroy(): Error deleting shelter in service');
      })
    );
  }

  findPets(id: number) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.get<Pet[]>(this.url + '/pets/' + id).pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('ShelterService.destroy(): Error deleting shelter in service');
      })
    );
  }
}
