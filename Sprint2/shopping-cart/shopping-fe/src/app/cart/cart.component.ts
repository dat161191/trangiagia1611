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

    }

  }
}
