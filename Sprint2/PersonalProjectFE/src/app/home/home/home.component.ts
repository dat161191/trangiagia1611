import {Component, OnInit} from '@angular/core';
import {ClockService} from '../../service/clock.service';
import {ClockInfoJson} from '../../clock-info-json';
import {ToastrService} from 'ngx-toastr';
import {Trademark} from '../../enity/clock/trademark';
import {MachineType} from '../../enity/clock/machine-type';

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

  constructor(private clockService: ClockService, private toastrService: ToastrService) {
  }

  ngOnInit(): void {
    this.getAllList(this.request);
  }

  getAllList(request: { page: number; size: number } | undefined): void {
    this.clockService.getListClock(request, this.search).subscribe(data => {
      // console.log(data);
      this.clockList = data[0];
      this.trademarks = data[1];
      this.machineTypes = data[2];
      // console.log(this.trademarks, this.machineTypes);
      // console.log(this.clockList);
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
