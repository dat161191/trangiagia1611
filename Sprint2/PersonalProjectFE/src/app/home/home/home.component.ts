import {Component, OnInit} from '@angular/core';
import {ClockService} from '../../service/clock.service';
import {ClockInfoJson} from '../../clock-info-json';
import {ToastrService} from 'ngx-toastr';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  clockList!: ClockInfoJson;
  request = {page: 0, size: 8};
  pageNumber = 0;
  search = '';

  constructor(private clockService: ClockService, private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.getAllList(this.request);
  }

  getAllList(request: { page: number; size: number } | undefined): void {
    this.clockService.getListClock(request, this.search).subscribe(data => {
      this.clockList = data;
      console.log(this.clockList);
    }, error => {
      this.clockList.content = [];
      this.toastrService.error('Không tìm ra sản phẩm', 'Lỗi', {timeOut: 2000});
    });

  }

  changePage(pageNumber: number) {
    this.request.page = pageNumber;
    this.ngOnInit();
  }

  searchClock(search: string, flag: boolean) {
    if (!flag) {
      this.request.page = 0;
    }
    this.search = search;
    this.ngOnInit();
  }
}
