import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ClockDetail} from '../enity/clock/clock-detail';
import {ImgDto} from '../enity/clock/img-dto';
import {ClockHome} from '../enity/clock/clock-home';
import {ClockCreate} from '../enity/clock/clock-create';

@Injectable({
  providedIn: 'root'
})
export class ClockService {
  urlClockList = 'http://localhost:8080/api/public/clock/list';
  urlClockDetail = 'http://localhost:8080/api/public/clock/detail';
  urlImgListByIdClock = 'http://localhost:8080/api/public/img';
  urlClockListByTradeId = 'http://localhost:8080/api/public/clock/detail-by-trademark';
  urlTrademarks = 'http://localhost:8080/api/public/trademark';
  urlMachineTypes = 'http://localhost:8080/api/public/machine';
  urlClockCreate = 'http://localhost:8080/api/admin/create-clock';
  urlClockHeader = 'http://localhost:8080/api/public/clock/header';

  constructor(private httpClient: HttpClient) {
  }

  getListClock(request: any, search: string): Observable<any> {
    const params = request;
    // console.log(this.urlClockList + '?search=' + search, {params});
    return this.httpClient.get(this.urlClockList + '?search=' + search, {params});
  }

  getListHeader():Observable<any>{
    return this.httpClient.get(this.urlClockHeader);
  }
  findById(id: number): Observable<ClockDetail> {
    // console.log(this.urlClockList + '/' + id);
    return this.httpClient.get<ClockDetail>(this.urlClockDetail + '/' + id);
  }

  findImgByIdClock(id: number): Observable<ImgDto[]> {
    // console.log(this.urlImgListByIdClock + '/' + id);
    return this.httpClient.get<ImgDto[]>(this.urlImgListByIdClock + '/' + id);
  }

  getListByTrademarkId(id: number): Observable<any> {
    // console.log(this.urlClockListByTradeId + '/' + id);
    return this.httpClient.get<ClockHome[]>(this.urlClockListByTradeId + '/' + id);
  }

  getTrademarks(): Observable<any> {
    return this.httpClient.get(this.urlTrademarks);
  }

  getMachineType(): Observable<any> {
    return this.httpClient.get(this.urlMachineTypes);
  }

  saveClock(clock: ClockCreate): Observable<any> {
    console.log(clock);
    return this.httpClient.post(this.urlClockCreate, clock);
  }
}
