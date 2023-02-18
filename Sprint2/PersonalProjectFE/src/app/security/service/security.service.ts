import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SingInForm} from '../../enity/account/sing-in-form';
import {Observable} from 'rxjs';
import {JwtResponse} from '../../enity/account/jwt-response';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private API_SIGNIN = 'http://localhost:8080/api/public/signin';

  constructor(private httpClient: HttpClient) {
  }

  signIn(signInForm: SingInForm): Observable<any> {
    return this.httpClient.post<JwtResponse>(this.API_SIGNIN, signInForm);
  }
}
