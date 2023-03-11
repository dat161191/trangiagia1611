import {Component, ElementRef, OnInit, ViewChild} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {TokenService} from '../security/service/token.service';
import {CartService} from '../service/cart.service';
import {Title} from '@angular/platform-browser';
import {CartListByIdAccount} from '../enity/cart/cart-list-by-id-account';
import {RequestCart} from '../enity/cart/request-cart';
import {BehaviorService} from '../service/behavior.service';
import {render} from 'creditcardpayments/creditCardPayments';
import {Customer} from '../enity/customer/customer';
import {CustomerService} from '../service/customer.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {OrdersRequest} from '../enity/order/ordersRequest';

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
  requestCart: RequestCart = {};
  customer: Customer = {};
  formOrder: FormGroup = new FormGroup({});
  ordersRequest: OrdersRequest = {};

  constructor(private toastrService: ToastrService,
              private router: Router,
              private tokenService: TokenService,
              private cartService: CartService,
              private title: Title,
              private activatedRoute: ActivatedRoute,
              private behaviorService: BehaviorService,
              private customerService: CustomerService) {

    this.title.setTitle('Giỏ Hàng');
    this.formOrder = new FormGroup({
      id: new FormControl(''),//id customer
      customerName: new FormControl('', Validators.required),
      phone: new FormControl('', Validators.required),
      email: new FormControl('', Validators.required),
      addressCustomer: new FormControl('', Validators.required),
      deliveryAddress: new FormControl('', Validators.required),
    });
  }


  ngOnInit(): void {
    this.idAccount = Number(this.tokenService.getId());
    this.getListCart();
    this.getCustomerByIdAccount();
  }

  getListCart() {
    this.cartService.getListCartByIdAccount(this.idAccount).subscribe(data => {
      this.cartListByIdAccount = data;
      this.getTotalPay();
      this.getTotalProduct();
      this.behaviorService.setCartTotal(String(this.cartListByIdAccount.length));
    }, error => {
      this.cartListByIdAccount.length = 0;
      this.behaviorService.setCartTotal(String(this.cartListByIdAccount.length));
    }, () => {
    });
  }

  getTotalPay() {
    this.totalPay = 0;
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      // @ts-ignore
      this.totalPay += (this.cartListByIdAccount[i].clockPrince * this.cartListByIdAccount[i].quanlityCart);
    }
  }

  getTotalProduct() {
    this.totalProduct = 0;
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      this.totalProduct += 1;
    }
  }

  /**
   * 06/03/2023 update
   * @param cartId
   */
  subQuanlity(cartId: number) {
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      if (this.cartListByIdAccount[i].cartId == cartId) {
        // @ts-ignore
        if (this.cartListByIdAccount[i]?.quanlityCart <= 1) {
          this.cartListByIdAccount[i].quanlityCart = 1;
          this.requestCart.quanlityUpdate = this.cartListByIdAccount[i].quanlityCart;
          this.toastrService.warning('Số lượng sản phẩm bạn đặt không được < 1', 'Cảnh báo', {timeOut: 2000});
        } else {
          // @ts-ignore
          this.cartListByIdAccount[i].quanlityCart -= 1;
          this.requestCart.quanlityUpdate = this.cartListByIdAccount[i].quanlityCart;
        }
      }
    }
    this.requestCart.idCart = cartId;
    this.requestCart.idAccount = Number(this.tokenService.getId());
    this.cartService.changeQuanlity(this.requestCart).subscribe(data => {
      this.cartListByIdAccount = data;
      this.getTotalPay();
    });
  }

  /**
   * 06/03/2023 update
   * @param cartId
   */
  addQuanlity(cartId: number) {
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      if (this.cartListByIdAccount[i].cartId == cartId) {
        // @ts-ignore
        if (this.cartListByIdAccount[i]?.quanlityCart >= this.cartListByIdAccount[i].quanlityClock) {
          this.cartListByIdAccount[i].quanlityCart = this.cartListByIdAccount[i].quanlityClock;
          this.requestCart.quanlityUpdate = this.cartListByIdAccount[i].quanlityCart;
          this.toastrService.info('Số sản phẩm bạn đặt nhiều hơn trong kho', 'Thông báo', {timeOut: 2000});
        } else {
          // @ts-ignore
          this.cartListByIdAccount[i].quanlityCart += 1;
          this.requestCart.quanlityUpdate = this.cartListByIdAccount[i].quanlityCart;
        }
      }
    }
    this.requestCart.idCart = cartId;
    this.requestCart.idAccount = Number(this.tokenService.getId());
    this.cartService.changeQuanlity(this.requestCart).subscribe(data => {
      this.cartListByIdAccount = data;
      this.getTotalPay();
    });

  }

  /**
   * 06/03/2023 update
   * @param value
   * @param cartId
   */
  change(value: number, cartId: number) {
    for (let i = 0; i < this.cartListByIdAccount.length; i++) {
      if (this.cartListByIdAccount[i].cartId == cartId) {
        // @ts-ignore
        if (value >= this.cartListByIdAccount[i].quanlityClock) {
          // @ts-ignore
          value = this.cartListByIdAccount[i].quanlityClock;
          // @ts-ignore
          this.cartListByIdAccount[i].quanlityCart = this.cartListByIdAccount[i].quanlityClock;
          this.toastrService.info('Số sản phẩm bạn đặt nhiều hơn trong kho', 'Thông báo', {timeOut: 2000});
          // this.ngOnInit();
        } else if (value < 1) {
          value = 1;
          this.cartListByIdAccount[i].quanlityCart = 1;
          // this.ngOnInit();
          this.toastrService.warning('Số lượng sản phẩm bạn đặt không được < 1', 'Cảnh báo', {timeOut: 2000});

        } else if (isNaN(value)) {
          // @ts-ignore
          value = this.cartListByIdAccount[i].quanlityCart;
          this.router.navigateByUrl('');
          this.toastrService.error('Bạn cố tình nhập sai', 'Cảnh cáo', {timeOut: 2000});
        } else {
          value = value;
        }
      }
    }
    this.requestCart.idCart = cartId;
    this.requestCart.quanlityUpdate = value;
    this.requestCart.idAccount = Number(this.tokenService.getId());
    this.cartService.changeQuanlity(this.requestCart).subscribe(data => {
      this.cartListByIdAccount = data;
      this.getTotalPay();
    });

  }

  /**
   * 06/03/2023 update
   * @param cartId
   */
  delete() {
    this.cartService.deleteById(this.temp).subscribe(data => {
      // this.ngOnInit();
      this.getListCart();
      this.toastrService.success('Bạn đã xoá: ' + this.temp.clockName, 'Thông Báo.', {timeOut: 2000});
    });
  }


  /**
   * 06/03/2023 update
   */
  payProduct() {
    this.orders();
    this.idAccount = Number(this.tokenService.getId());
    this.cartService.payCart(this.idAccount).subscribe(data => {
      this.cartListByIdAccount = data;
      this.getTotalPay();
      this.getListCart();
      this.toastrService.success('Bạn đã thanh toán thành công.Xin cảm ơn!', 'Thông báo', {timeOut: 2000});
    });
  }

  getCustomerByIdAccount() {
    this.customerService.getCustomerByIdAccount(this.idAccount).subscribe(data => {
      this.customer = data;
      this.formOrder.patchValue(this.customer);
    });

  }

  payClock() {
    render({
      id: '#paypalbutton',
      currency: 'USD',
      value: (this.totalPay / 24000).toFixed(0),
      onApprove: (details => {
        this.payProduct();
      })
    });
  }

  reset() {
    console.log('RSet payPal');
    location.reload();
  }

  orders() {
    if (this.formOrder.value.deliveryAddress==""){
      this.formOrder.value.deliveryAddress=this.customer.addressCustomer;
    }
    this.ordersRequest = this.formOrder.value;
    this.ordersRequest.totalOrder = this.totalPay;
    this.cartService.importOrder(this.ordersRequest).subscribe(data => {
      // this.toastrService.success('Thên mới đơn hàng thành công', 'Thông báo', {timeOut: 2000});
    }, error => {
    }, () => {
    });
  }
}
