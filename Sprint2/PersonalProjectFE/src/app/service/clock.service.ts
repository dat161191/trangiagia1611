import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {
  urlProductList = 'http://localhost:8080/api/pulic/clock';

  constructor(private httpClient: HttpClient) {
  }

  getListProduct(request: any): Observable<any> {
    const params = request;
    return this.httpClient.get(this.urlProductList, {params});
  }
}
