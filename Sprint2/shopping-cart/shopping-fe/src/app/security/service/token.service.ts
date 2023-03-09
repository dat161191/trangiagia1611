import {Injectable} from '@angular/core';

const TOKEN_KEY = 'Token_key';
const NAME_KEY = 'Name_key';
const ROLE_KEY = 'Role_key';
const ID_KEY = 'Id_key';
const EMAIL_KEY = 'Email_key';
const AVATAR_KEY = 'Avatar_key';
const ENCODER_KEY = 'Encoder_key';
const ID_CUSTOMER = 'Id_customer_key';

@Injectable({
  providedIn: 'root'
})
export class TokenService {

  private _roles: [] = [];

  constructor() {
  }

  public setAvatar(avatar: string): void {
    localStorage.removeItem(AVATAR_KEY);
    localStorage.setItem(AVATAR_KEY, avatar);
  }

  public getAvatar(): string | null {
    return localStorage.getItem(AVATAR_KEY);
  }

  public setEmail(email: string): void {
    localStorage.removeItem(EMAIL_KEY);
    localStorage.setItem(EMAIL_KEY, email);
  }

  public getEmail(): string | null {
    return localStorage.getItem(EMAIL_KEY);
  }

  public setId(id: string): void {
    localStorage.removeItem(ID_KEY);
    localStorage.setItem(ID_KEY, id);
  }

  public getId(): string | null {
    return localStorage.getItem(ID_KEY);
  }

  public setToken(token: string): void {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.setItem(TOKEN_KEY, token);
  }

  public getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }

  public setName(name: string): void {
    localStorage.removeItem(NAME_KEY);
    localStorage.setItem(NAME_KEY, name);
  }

  public getEncoder(): string | null {
    return localStorage.getItem(ENCODER_KEY);
  }

  public setEncoder(encoder: string): void {
    localStorage.removeItem(ENCODER_KEY);
    localStorage.setItem(ENCODER_KEY, encoder);
  }

  public getIdCustomer(): string | null {
    return localStorage.getItem(ID_CUSTOMER);
  }

  public setIdCustomer(idCustomer: string): void {
    localStorage.removeItem(ID_CUSTOMER);
    localStorage.setItem(ID_CUSTOMER, idCustomer);
  }

  public getName(): string | null {
    return localStorage.getItem(NAME_KEY);
  }


  public setRole(roles: string[]): void {
    localStorage.removeItem(ROLE_KEY);
    localStorage.setItem(ROLE_KEY, JSON.stringify(roles));
  }


  public getRole(): string[] {
    this._roles = [];
    if (this.getToken()) {
      // @ts-ignore
      JSON.parse(localStorage.getItem(ROLE_KEY)).forEach(role => {
        // @ts-ignore
        this._roles.push(role.authority);
      });
    }
    return this._roles;
  }

  public rememberMe(roles: string[], name: string, token: string): void {
    this.setRole(roles);
    this.setName(name);
    this.setToken(token);
  }
}
