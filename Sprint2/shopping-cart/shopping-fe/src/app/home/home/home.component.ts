import {Component, Input, OnInit} from '@angular/core';
import {ClockService} from '../../service/clock.service';
import {ClockInfoJson} from '../../clock-info-json';
import {ToastrService} from 'ngx-toastr';
import {ClockHome} from '../../enity/clock/clock-home';
import {BehaviorService} from '../../service/behavior.service';
import {TokenService} from '../../security/service/token.service';
import {CartListByIdAccount} from '../../enity/cart/cart-list-by-id-account';
import {CartService} from '../../service/cart.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  clockList!: ClockInfoJson;
  request = {page: 0, size: 8};
  search = '';
  clockCarousel: ClockHome[] = [];
  cartListByIdAccount: CartListByIdAccount[] = [];
  idAccount: number = -1;


  constructor(private clockService: ClockService,
              private toastrService: ToastrService,
              private cartService: CartService,
              private behaviorService: BehaviorService,
              private tokenService: TokenService) {
    this.clockService.getListHeader().subscribe(clocs => {
      this.clockCarousel = clocs;
    });
  }

  ngOnInit(): void {
    this.getAllList(this.request);
    this.getValueHeader();
    this.idAccount = Number(this.tokenService.getId());
    if (this.idAccount != null) {
      this.getListCart();
    }
  }

  getAllList(request: { page: number; size: number } | undefined): void {
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
    this.behaviorService.getValue().subscribe(data => {
      this.search = data;
      this.getAllList(this.request);
    });
  }

  getListCart() {
    this.cartService.getListCartByIdAccount(this.idAccount).subscribe(data => {
      this.cartListByIdAccount = data;
      this.behaviorService.setCartTotal(String(this.cartListByIdAccount.length));
    }, error => {
      this.cartListByIdAccount.length = 0;
      this.behaviorService.setCartTotal(String(this.cartListByIdAccount.length));
    });
  }

  scroll() {
    window.scrollTo(0, 0);
  }
}
