import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ClockDetail} from '../enity/clock/clock-detail';
import {ImgDto} from '../enity/clock/img-dto';
import {ClockHome} from '../enity/clock/clock-home';

@Injectable({
  providedIn: 'root'
})
export class ClockService {
  urlClockList = 'http://localhost:8080/api/pulic/clock';
  urlImgListByIdClock='http://localhost:8080/api/img'
  urlClockListByTradeId='http://localhost:8080/api/pulic/clock/detail'

  constructor(private httpClient: HttpClient) {
  }

  getListClock(request: any): Observable<any> {
    const params = request;
    // console.log(this.urlClockList,this.urlClockList, {params});
    return this.httpClient.get(this.urlClockList, {params});
  }

  findById(id: number): Observable<ClockDetail> {
    // console.log(this.urlClockList + '/' + id);
    return this.httpClient.get<ClockDetail>(this.urlClockList + '/' + id);
  }
  findImgByIdClock(id: number): Observable<ImgDto[]> {
    // console.log(this.urlImgListByIdClock + '/' + id);
    return this.httpClient.get<ImgDto[]>(this.urlImgListByIdClock + '/' + id);
  }

  getListByTrademarkId(id: number):Observable<any>{
    console.log(this.urlClockListByTradeId + '/' + id);
    return this.httpClient.get<ClockHome[]>(this.urlClockListByTradeId + '/' + id);
  }
}
