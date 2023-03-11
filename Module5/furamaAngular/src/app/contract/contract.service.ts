import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Contract} from "./Contract";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class ContractService {
  URLCT = 'http://localhost:3000/contracts'

  constructor(private http: HttpClient) {
  }

  // getAll(searchKey: any = null): Observable<Contract[]> {
  //   let urlSearch = this.URLCT;
  //   if (searchKey != null) {
  //     urlSearch += '?name_like=' + searchKey
  //   }
  //   console.log(urlSearch)
  //   return this.http.get<Contract[]>(urlSearch);
  // }
  getAll(searchName: any = null, searchId: any = null): Observable<Contract[]> {
    let urlSearch = this.URLCT;
    if (searchName && searchId) {
      urlSearch += '?name_like=' + searchName + '&id_like=' + searchId;
    }
    if (searchName && !searchId) {
      urlSearch += '?name_like=' + searchName;
    }
    if (!searchName && searchId) {
      urlSearch += '?id_like=' + searchId;
    }
    console.log(urlSearch)
    return this.http.get<Contract[]>(urlSearch);
  }

  findById(id: number): Observable<Contract> {
    return this.http.get<Contract>(this.URLCT + '/' + id);
  }

  deleteById(id: number | undefined): Observable<Contract> {
    return this.http.delete<Contract>(this.URLCT + '/' + id);
  }

  save(contract: Contract): Observable<Contract> {
    return this.http.post<any>(this.URLCT, contract);
  }

  edit(id: number, contract: Contract): Observable<Contract> {
    console.log(contract)
    return this.http.patch<Contract>(this.URLCT + '/' + id, contract);
  }
}
