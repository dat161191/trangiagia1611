import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {ClockService} from '../../service/clock.service';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {ToastrService} from 'ngx-toastr';
import {ClockDetail} from '../../enity/clock/clock-detail';
import {ImgDto} from '../../enity/clock/img-dto';
import {ClockHome} from '../../enity/clock/clock-home';
import {OwlOptions} from 'ngx-owl-carousel-o';

@Component({
  selector: 'app-detail-clock',
  templateUrl: './detail-clock.component.html',
  styleUrls: ['./detail-clock.component.css']
})
export class DetailClockComponent implements OnInit {
  clockDetail: ClockDetail = {};
  imgList: ImgDto[] = [];
  idClock: number = 0;
  clockListByTrademark: ClockHome[] = [];
  idTrademark: number = 0;

  customOptions: OwlOptions = {
    loop: true,
    mouseDrag: false,
    touchDrag: true,
    pullDrag: false,
    dots: false,
    // margin: 5,
    autoplay: true,
    navSpeed: 200,
    navText: ['', ''],
    responsive: {
      0: {
        items: 1
      },
      400: {
        items: 2
      },
      740: {
        items: 4
      }
    },
    nav: true
  };

  constructor(private clockService: ClockService, private activatedRoute: ActivatedRoute,
              private title: Title, private router: Router, private toastrService: ToastrService) {
    this.activatedRoute.paramMap.subscribe(data => {
      // @ts-ignore
      this.idClock = +data.get('id');
      if (this.idClock != null) {
        this.getClockDetai();
        this.getImgListByIdClock();
      }
    });
  }


  ngOnInit(): void {

  }

  private static resetJS() {
    const script = document.createElement('script');
    script.src = 'assets/jshtml/detail-clock.js';
    document.body.appendChild(script);
  }

  getClockDetai() {
    this.clockService.findById(this.idClock).subscribe(result => {
      this.clockDetail = result;
      this.toastrService.success('', 'Thông tin sản phẩm: ' + this.clockDetail.name, {timeOut: 3000});
      // @ts-ignore
      this.idTrademark = this.clockDetail.trademark.id;
      // @ts-ignore
      this.getClockListByTrademark();
      DetailClockComponent.resetJS();
    }, error => {
      this.router.navigateByUrl('');
      this.toastrService.error('Không có sản phẩm này trong danh sách.', 'Lỗi.', {timeOut: 2000});
    }, () => {
    });
  }

  getImgListByIdClock() {
    this.clockService.findImgByIdClock(this.idClock).subscribe(result => {
      this.imgList = result;
      console.log(this.imgList);
    }, error => {
    }, () => {
    });
  }

  getClockListByTrademark() {
    // @ts-ignore
    this.clockService.getListByTrademarkId(this.idTrademark).subscribe(data => {
      this.clockListByTrademark = data;
      // console.log(this.clockListByTrademark);
    }, error => {
    }, () => {
    });
  }

}
