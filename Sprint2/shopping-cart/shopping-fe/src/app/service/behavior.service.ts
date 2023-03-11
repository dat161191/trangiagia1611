import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BehaviorService {
  /* BehaviorService: cung cấp một cách để lưu trữ và chia sẻ giá trị.
  * Cho phép các thành phần khác nhau của ứng dụng truy cập vào giá trị này và được cập nhật khi giá trị thay đổi.*/
  private resultSearch: BehaviorSubject<any> = new BehaviorSubject<any>('');
  private cartTotal: BehaviorSubject<any> = new BehaviorSubject<any>('');

  constructor() {
  }

  /*
  * setValue(value: string) là một phương thức của lớp, được sử dụng để cập nhật giá trị của biến resultSearch bằng giá trị mới được cung cấp trong tham số value.
  * Khi được gọi, phương thức này sẽ gọi phương thức next() của biến resultSearch để phát ra giá trị mới nhất của biến này đến các subscriber của Observable.
  * Bất kỳ thành phần nào đã subscribe vào biến resultSearch sẽ nhận được giá trị mới nhất của biến này thông qua Observable.*/
  setValue(value: string) {
    this.resultSearch.next(value);
  }

  /*Phương thức asObservable() sẽ trả về một Observable của biến resultSearch.
  Bất kỳ thành phần nào trong ứng dụng có thể subscribe vào Observable này để nhận các giá trị mới nhất.
  Khi một subscriber subscribe vào biến resultSearch, phương thức getValue() sẽ trả về các giá trị mới nhất của biến resultSearch thông qua Observable này.*/
  getValue(): Observable<any> {
    return this.resultSearch.asObservable();
  }

  setCartTotal(value: string): void {
    this.cartTotal.next(value);
  }

  getCartTotal(): Observable<any> {
    return this.cartTotal.asObservable();
  }

  setToTalPay(value: number): void {
    this.cartTotal.next(value);
  }

  getToTalPay(): Observable<number> {
    return this.cartTotal.asObservable();
  }
}
