import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {CustomerType} from "../CustomerType";

@Injectable({
  providedIn: 'root'
})
export class CustomerTypeService {
  URL = 'http://localhost:3000/customerTypes';

  constructor(private http: HttpClient) {
    this.getAll()
  }

  getAll(): Observable<CustomerType[]> {
    return this.http.get<CustomerType[]>(this.URL)
  }
}
