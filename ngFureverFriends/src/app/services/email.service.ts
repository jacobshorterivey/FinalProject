import { Injectable } from '@angular/core';
import { EmailServiceImpl } from '../models/email';
import { environment } from 'src/environments/environment';
import { HttpClient } from '@angular/common/http';
import { catchError } from 'rxjs/operators';
import { throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class EmailService {

  // F I E L D S
  baseUrl = environment.baseUrl;
  url = this.baseUrl + 'api/mail';

  // C O N S T R U C T O R
  constructor(private http: HttpClient) { }

  // M E T H O D S
  send(email: EmailServiceImpl) {
    const httpOptions = {
      headers: {
        'Content-type': 'application/json',
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
    return this.http.post<EmailServiceImpl>(this.url, email, httpOptions).pipe(
      catchError((err: any) => {
        // console.log(err);
        return throwError('Sent email failed.');
      })
    );
  }
}
