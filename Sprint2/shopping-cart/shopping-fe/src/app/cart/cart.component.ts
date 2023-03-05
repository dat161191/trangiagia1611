import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenService} from '../security/service/token.service';
import {CartService} from '../service/cart.service';
import {Title} from '@angular/platform-browser';
import {CartListByIdAccount} from '../enity/cart/cart-list-by-id-account';


@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  idAccount: number = -1;
  cartListByIdAccount: CartListByIdAccount[] = [];
  temp: CartListByIdAccount = {};
  totalProduct = 0;
  totalPay = 0;
  sale = 0;
  idCustomer = -1;
  messPay = '';
  request = {idCard: -1,quanlityCart: -1};

  constructor(private toastrService: ToastrService,
              private router: Router,
              private tokenService: TokenService,
              private cartService: CartService,
              private title: Title, private activatedRoute: ActivatedRoute) {
    this.title.setTitle('Giỏ Hàng');
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe(data => {
      this.idAccount = Number(data.get('id'));
      if (this.idAccount != null) {
        this.getListCart();
      }
    });
  }

  getListCart() {
    this.cartService.getListCartByIdAccount(this.idAccount).subscribe(data => {
      this.cartListByIdAccount = data;
      this.getTotalProduct();
      this.getTotalPay();
    });
  }

  subQuanlity(cartId: number) {
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      if (this.cartListByIdAccount[i].cartId == cartId) {
        // @ts-ignore
        if (this.cartListByIdAccount[i]?.quanlityCart <= 1) {
          this.cartListByIdAccount[i].quanlityCart = 1;
          this.toastrService.warning('Số lượng sản phẩm bạn đặt không được < 1', 'Cảnh báo', {timeOut: 2000});
        } else {
          // @ts-ignore
          this.cartListByIdAccount[i].quanlityCart -= 1;
        }
      }
    }
  }

  addQuanlity(cartId: number) {
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      if (this.cartListByIdAccount[i].cartId == cartId) {
        // @ts-ignore
        if (this.cartListByIdAccount[i]?.quanlityCart >= this.cartListByIdAccount[i].quanlityClock) {
          this.cartListByIdAccount[i].quanlityCart = this.cartListByIdAccount[i].quanlityClock;
          this.toastrService.info('Số sản phẩm bạn đặt nhiều hơn trong kho', 'Thông báo', {timeOut: 2000});
        } else {
          // @ts-ignore
          this.cartListByIdAccount[i].quanlityCart += 1;
        }
      }
    }
  }

  change(value: number, cartId: number) {
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      if (this.cartListByIdAccount[i].cartId == cartId) {

        // @ts-ignore
        if (value >= this.cartListByIdAccount[i].quanlityClock) {
          // @ts-ignore
          this.cartListByIdAccount[i].quanlityCart = this.cartListByIdAccount[i].quanlityClock;
          this.toastrService.info('Số sản phẩm bạn đặt nhiều hơn trong kho', 'Thông báo', {timeOut: 2000});
        } else if (value <= 1) {
          this.cartListByIdAccount[i].quanlityCart = 1;
          this.toastrService.warning('Số lượng sản phẩm bạn đặt không được < 1', 'Cảnh báo', {timeOut: 2000});
        } else if (isNaN(value)) {
          this.router.navigateByUrl('');
          this.toastrService.error('Bạn cố tình nhập sai', 'Cảnh cáo', {timeOut: 2000});
        }
      }
      this.request={idCard: cartId,quanlityCart: value}
      this.cartService.changeQuanlity(this.request)
    }

  }

  delete() {
    this.cartService.deleteById(this.temp).subscribe(data => {
      this.ngOnInit();
      this.toastrService.success('Bạn đã xoá: ' + this.temp.clockName, 'Thông Báo.', {timeOut: 2000});
    });
  }

  private getTotalProduct() {
    this.totalProduct = 0;
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      this.totalProduct += 1;
    }
  }

  private getTotalPay() {
    this.totalPay = 0;
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      // @ts-ignore
      this.totalPay += this.cartListByIdAccount[i].clockPrince * this.cartListByIdAccount[i].quanlityCart;
    }
  }

  payProduct() {
    this.idAccount = Number(this.tokenService.getId());
    this.cartService.payCart(this.idAccount).subscribe(data => {
      this.cartListByIdAccount = data;
      this.toastrService.success('Bạn đã thanh toán thành công.Xin cảm ơn!', 'Thông báo', {timeOut: 2000});
    });
  }
}
