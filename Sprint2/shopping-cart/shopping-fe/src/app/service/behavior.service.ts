import {Injectable} from '@angular/core';
import {BehaviorSubject, Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BehaviorService {
  private resultSearch: BehaviorSubject<any> = new BehaviorSubject<any>('');
  private cartTotal: BehaviorSubject<any> = new BehaviorSubject<any>('');

  constructor() {
  }

  setValue(value: string) {
    this.resultSearch.next(value);
  }

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
