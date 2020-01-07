import { Pet } from './../models/pet';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { throwError, Observable } from 'rxjs';
import { Shelter } from '../models/shelter';
import { AuthService } from './auth.service';
import { Breed } from '../models/breed';
import { Trait } from '../models/trait';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PetService {
  // baseUrl = 'http://localhost:8087/';
  baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/pet';

  constructor(private http: HttpClient, private auth: AuthService) { }

  // If you need to, headers can be added to an HTTP Request by using the HttpHeaders object.
  // it is used ONLY inside of functions
  // const httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type':  'application/json',
  //     Authorization: 'my-auth-token'
  //   })
  // };

  // Methods

    // Gets
    index() {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest'
        })
      };
      return this.http.get<Pet[]>(this.url, httpOptions).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError('PetService.index(): Error retrieving pets');
        })
      );
    }

    nameTraitsBreedSearch(keyword: string) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest'
        })
      };
      return this.http.get<Pet[]>(this.url + '/search/' + keyword, httpOptions).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError('PetService.nameTraitsBreedSearch(): Error retrieving pets');
        })
      );
    }

    showOne(id: string) {
      const httpOptions = {
        headers: new HttpHeaders({
          'Content-type': 'application/json',
          'X-Requested-With': 'XMLHttpRequest'
        })
      };
      return this.http.get<Pet>(this.url + '/' + id, httpOptions).pipe(
        catchError((err: any) => {
          console.error(err);
          return throwError('Pet update error');
        })
      );
    }
      show(id): Observable<Pet> {
      return this.http.get<Pet>(this.url + '/' + id);
    }

  // gets single shelter per pet
  getShelter(id: number) {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Shelter>(this.url + '/shelter/' + id)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('find shelter failed');
      })
    );
  }

  // Create/Post
  create(data: Pet) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(),
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.post<Pet>(this.url + '/create', data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('create failed');
      })
    );
  }

  // Update
  update(id: number, data: Pet) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    console.log('in update');
    console.log(data);

    return this.http.put<Pet>(this.url + '/' + id, data, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('update failed');
      })
    );
  }
    // Delete
  destroy(id: number) {
    const httpOptions = {
      headers: {
        Authorization: 'Basic ' + this.auth.getCredentials(), 'Content-type': 'application/json', 'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.delete<Pet>(this.url + '/delete/' + id, httpOptions)
    .pipe(
      catchError((err: any) => {
        console.log(err);
        return throwError('update failed');
      })
    );
  }

  // Get list of breeds
  indexBreed() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Breed[]>(this.url + '/breed', httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('PetService.indexBreed(): Error retrieving breeds');
      })
    );
  }

  // Get list of traits
  indexTrait() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      })
    };
    return this.http.get<Trait[]>(this.url + '/trait', httpOptions).pipe(
      catchError((err: any) => {
        console.error(err);
        return throwError('PetService.indexBreed(): Error retrieving breeds');
      })
    );
  }
}
