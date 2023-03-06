import {Component, OnChanges, OnDestroy, OnInit, SimpleChanges} from '@angular/core';
import {ClockService} from '../../service/clock.service';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {Title} from '@angular/platform-browser';
import {ToastrService} from 'ngx-toastr';
import {ClockDetail} from '../../enity/clock/clock-detail';
import {ImgDto} from '../../enity/clock/img-dto';
import {ClockHome} from '../../enity/clock/clock-home';
import {OwlOptions} from 'ngx-owl-carousel-o';
import {TokenService} from '../../security/service/token.service';
import {CartCreate} from '../../enity/cart/cart-create';
import {CartService} from '../../service/cart.service';
import {BehaviorService} from '../../service/behavior.service';
import {CartListByIdAccount} from '../../enity/cart/cart-list-by-id-account';

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
  checkLogin = false;
  name: string | null | undefined;
  roles: string[] = [];
  idAccount: string | null | undefined;
  cartCreate: CartCreate = {};
  numberPay: number = 1;
  cartListByIdAccount: CartListByIdAccount[] = [];
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

  constructor(private clockService: ClockService, private activatedRoute: ActivatedRoute, private tokenService: TokenService,
              private title: Title, private router: Router, private toastrService: ToastrService, private cartService: CartService,
              private behaviorService: BehaviorService) {
    this.title.setTitle('Chi tiết sản phẩm.');

  }


  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(data => {
      // @ts-ignore
      this.idClock = +data.get('id');
      if (this.idClock != null) {
        this.getClockDetai();
        this.getImgListByIdClock();
      }
    }, error => {
    }, () => {
    });
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      this.name = this.tokenService.getName();
      this.roles = this.tokenService.getRole();
      this.idAccount = this.tokenService.getId();
    }
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

  subQuanlity() {
    if (this.numberPay <= 1) {
      this.numberPay = 1;
      this.toastrService.warning('Số lượng sản phẩm bạn đặt không được < 0', 'Cảnh báo', {timeOut: 2000});
    } else {
      this.numberPay -= 1;
    }
  }

  addQuanlity() {
    if ((this.clockDetail?.quanlity != undefined) && (this.clockDetail?.quanlity <= this.numberPay)) {
      this.numberPay = this.clockDetail.quanlity;
      this.toastrService.info('Số sản phẩm bạn đặt nhiều hơn trong kho', 'Thông báo', {timeOut: 2000});
    } else {
      this.numberPay += 1;
    }
  }

  addToCart(productDetail: any) {
    if (isNaN(this.numberPay)) {
      this.router.navigateByUrl('');
      this.toastrService.error('Bạn cố tình nhập sai', 'Cảnh cáo', {timeOut: 2000});
    } else {
      this.cartCreate.clock = productDetail;
      this.cartCreate.quantityPurchased = this.numberPay;
      this.cartCreate.idAccount = Number(this.idAccount);
      this.cartService.createCart(this.cartCreate).subscribe(data => {
        // @ts-ignore
        this.cartListByIdAccount = data;
        this.behaviorService.setCartTotal(String(this.cartListByIdAccount.length));
        this.numberPay = 1;
        this.toastrService.success('Bạn đã thêm ' + this.cartCreate.clock?.name + ' thành công.', 'Thông báo', {timeOut: 2000});
      }, error => {
        this.toastrService.error('Thêm vào giỏ hàng không thành công', '', {timeOut: 2000});
      });
    }

  }

  change(number: number) {
    if (isNaN(number)) {
      this.router.navigateByUrl('');
      this.toastrService.error('Bạn cố tình nhập sai', 'Cảnh cáo', {timeOut: 2000});
    } else if (number < 1) {
      this.numberPay = 1;
      this.toastrService.warning('Số lượng sản phẩm bạn đặt không được < 0', 'Cảnh báo', {timeOut: 2000});
    } else if ((this.clockDetail?.quanlity != undefined) && (this.clockDetail?.quanlity < number)) {
      this.numberPay = this.clockDetail.quanlity;
      this.toastrService.info('Số sản phẩm bạn đặt nhiều hơn trong kho', 'Thông báo', {timeOut: 2000});
    }
  }
}
