import {Component, Input, OnInit} from '@angular/core';
import {ClockService} from '../../service/clock.service';
import {ClockInfoJson} from '../../clock-info-json';
import {ToastrService} from 'ngx-toastr';
import {Trademark} from '../../enity/clock/trademark';
import {MachineType} from '../../enity/clock/machine-type';
import {ClockHome} from '../../enity/clock/clock-home';
import {SearchService} from '../../service/search.service';
import {TokenService} from '../../security/service/token.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  clockList!: ClockInfoJson;
  request = {page: 0, size: 8};
  trademarks: Trademark[] = [];
  machineTypes: MachineType[] = [];
  pageNumber = 0;
  search = '';
  clockCarousel: ClockHome[] = [];

  constructor(private clockService: ClockService, private toastrService: ToastrService,
              private searchService: SearchService,private tokenService:TokenService) {
    this.clockService.getListHeader().subscribe(clocs => {
      this.clockCarousel = clocs;
    });
  }

  ngOnInit(): void {
    this.getAllList(this.request);
    this.getValueHeader();
  }

  getAllList(request: { page: number; size: number } | undefined): void {
    console.log(this.tokenService.getRole());
    this.clockService.getListClock(request, this.search).subscribe(data => {
      this.clockList = data;
    }, error => {
      this.clockList.content = [];
      this.toastrService.error('Không tìm ra sản phẩm', 'Lỗi', {timeOut: 2000});
    });

  }

  changePage(pageNumber: number) {
    this.request.page = pageNumber;
    this.ngOnInit();
  }

  private getValueHeader() {
    this.searchService.getValue().subscribe(data => {
      this.search = data;
      this.getAllList(this.request);
    });
  }
}
