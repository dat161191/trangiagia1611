import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Employee} from "./Employee";

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {
  URL = 'http://localhost:3000/employee'

  constructor(private http: HttpClient) {
  }

  getAll(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.URL);
  }
}
