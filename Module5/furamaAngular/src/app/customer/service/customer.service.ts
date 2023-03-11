import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Customer} from "../Customer";

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  URLCTM = 'http://localhost:3000/customer';


  constructor(private http: HttpClient) {
  }

  getAll(searKey: any = null): Observable<Customer[]> {
    let urlSearh = this.URLCTM;
    if (searKey != null) {
      urlSearh += '?name_like=' + searKey;
    }
    // console.log(urlSearh)
    return this.http.get<Customer[]>(urlSearh)
  }


  deleteById(id: number | undefined): Observable<Customer> {
    return this.http.delete<Customer>(this.URLCTM + '/' + id);
  }

  findById(id: number): Observable<Customer> {
    return this.http.get<Customer>(this.URLCTM + '/' + id);
  }

  saveCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.URLCTM, customer);
  }

  editCustomer(customer: Customer): Observable<Customer> {
    return this.http.patch<Customer>(this.URLCTM + '/' + customer.id, customer);
  }

}
