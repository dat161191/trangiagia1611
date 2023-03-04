import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CartCreate} from '../enity/cart/cart-create';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httpClient: HttpClient) {
  }

  createCart(cart: CartCreate) {
    return this.httpClient.post('http://localhost:8080/api/user/cart/create', cart);
  }

  getListCartByIdAccount(idAccount: any): Observable<any> {
    console.log('http://localhost:8080/api/user/cart/create' + '/' + idAccount);
    return this.httpClient.get('http://localhost:8080/api/user/cart/list' + '/' + idAccount);
  }
}
