import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Facility} from "../Facility";
import {Customer} from "../../customer/Customer";

@Injectable({
  providedIn: 'root'
})
export class FacilityService {
  URL = 'http://localhost:3000/facilities'

  constructor(private http: HttpClient) {
    this.getAll();
  }

  getAll(): Observable<Facility[]> {
    return this.http.get<Facility[]>(this.URL);
  }

  deleteById(id: number | undefined): Observable<Facility> {
    return this.http.delete<Facility>(this.URL + '/' + id);
  }
  findById(id: number): Observable<Facility> {
    return this.http.get<Facility>(this.URL + '/' + id);
  }
}
