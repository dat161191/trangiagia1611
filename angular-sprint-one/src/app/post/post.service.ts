import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Post} from "./interface/post";

@Injectable({
  providedIn: 'root'
})
export class PostService {
  urlSearch = 'http://localhost:8080/post/search';
  urlList = 'http://localhost:8080/post';

  constructor(private http: HttpClient) {
  }

  display(): Observable<Post[]> {
    return this.http.get<Post[]>(this.urlList);
  }
  search(month: string, year: string): Observable<Post[]> {
    console.log(this.urlSearch + '?year=' + year + '&month=' + month);
    return this.http.get<Post[]>(this.urlSearch + '?year=' + year + '&month=' + month);
  }
}
