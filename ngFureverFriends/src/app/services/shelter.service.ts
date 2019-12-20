import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ShelterService {
  baseUrl = 'http://localhost:8087/';
  // baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/shelter';

  constructor(private http: HttpClient) { }

  // If you need to, headers can be added to an HTTP Request by using the HttpHeaders object.
  // it is used ONLY inside of functions
  // const httpOptions = {
  //   headers: new HttpHeaders({
  //     'Content-Type':  'application/json',
  //     Authorization: 'my-auth-token'
  //   })
  // };
}
