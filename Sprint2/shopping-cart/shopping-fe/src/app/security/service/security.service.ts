import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SingInForm} from '../../enity/account/sing-in-form';
import {Observable} from 'rxjs';
import {JwtResponse} from '../../enity/account/jwt-response';
import {Customer} from '../../enity/customer/customer';

@Injectable({
  providedIn: 'root'
})
export class SecurityService {

  private API_SIGNIN = 'http://localhost:8080/api/public/signin';
  private API_REGISTER = 'http://localhost:8080/api/public/register';
  private API_CHANGE_AVATAR = 'http://localhost:8080/api/pulic/change-avatar';

  constructor(private httpClient: HttpClient) {
  }

  signIn(signInForm: SingInForm): Observable<any> {
    return this.httpClient.post<JwtResponse>(this.API_SIGNIN, signInForm);
  }

  resister(customer: Customer): Observable<Customer> {
    return this.httpClient.post<Customer>(this.API_REGISTER, customer);
  }

  changeAvatar(info: any): Observable<JwtResponse> {
    console.log(this.API_CHANGE_AVATAR, info);
    return this.httpClient.put<JwtResponse>(this.API_CHANGE_AVATAR, info);
  }
}
