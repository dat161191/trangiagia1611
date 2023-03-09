import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {CartCreate} from '../enity/cart/cart-create';
import {Observable} from 'rxjs';
import {CartListByIdAccount} from '../enity/cart/cart-list-by-id-account';
import {RequestCart} from '../enity/cart/request-cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private httpClient: HttpClient) {
  }

  createCart(cart: CartCreate):Observable<any> {
    console.log('http://localhost:8080/api/user/cart/create', cart);
    return this.httpClient.post('http://localhost:8080/api/user/cart/create', cart);
  }

  getListCartByIdAccount(idAccount: any): Observable<any> {
    console.log('http://localhost:8080/api/user/cart/create' + '/' + idAccount);
    return this.httpClient.get('http://localhost:8080/api/user/cart/list' + '/' + idAccount);
  }

  deleteById(temp: CartListByIdAccount): Observable<CartListByIdAccount> {
    return this.httpClient.delete<CartListByIdAccount>('http://localhost:8080/api/user/cart/delete' + '/' + temp.cartId);
  }

  /**
   * 05/03/2023
   * @param request
   */
  payCart(idAccount: number): Observable<any> {
    return this.httpClient.patch('http://localhost:8080/api/user/cart/pay-cart' + '/' + idAccount, idAccount);
  }

  /**
   * 06/03/2023
   * @param request
   */
  changeQuanlity(requestCart: RequestCart) :Observable<any>{
    // console.log('http://localhost:8080/api/user/cart/change-quanlity', requestCart);
    return this.httpClient.patch('http://localhost:8080/api/user/cart/change-quanlity', requestCart);
  }

  historyPay(idAccount: number):Observable<any>{
    console.log('http://localhost:8080/api/user/cart/history' + '/' + idAccount);
    return this.httpClient.get('http://localhost:8080/api/user/cart/history' + '/' + idAccount)
  }
}
