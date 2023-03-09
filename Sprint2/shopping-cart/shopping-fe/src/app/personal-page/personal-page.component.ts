import {Component, OnInit} from '@angular/core';
import {ChangeAvatar} from '../enity/account/ChangeAvatar';
import {SecurityService} from '../security/service/security.service';
import {TokenService} from '../security/service/token.service';
import {Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Customer} from '../enity/customer/customer';
import {CustomerService} from '../service/customer.service';
import {CartListByIdAccount} from '../enity/cart/cart-list-by-id-account';
import {CartService} from '../service/cart.service';

@Component({
  selector: 'app-personal-page',
  templateUrl: './personal-page.component.html',
  styleUrls: ['./personal-page.component.css']
})
export class PersonalPageComponent implements OnInit {
  status = 'Vui lòng chọn ảnh và click upload!';
  form: any = {};
  changeAvatar: ChangeAvatar | undefined;
  error: any = {
    message: 'no'
  };
  success: any = {
    message: 'yes'
  };
  avatarUser: string | null = '';
  nameUser: string | null = '';
  emailUser: string | null = '';
  customer: Customer = {};
  idAccount = -1;
  checkInfo: boolean = true;
  checkHistory: boolean = false;
  historyCart: CartListByIdAccount[] = [];
  p = 1;
  itemsPerPage= 5;


  constructor(private securityService: SecurityService,
              private tokenService: TokenService,
              private router: Router,
              private toastrService: ToastrService,
              private customerService: CustomerService,
              private cartService: CartService) {
    this.avatarUser = this.tokenService.getAvatar();
    this.nameUser = this.tokenService.getAvatar();
    this.emailUser = this.tokenService.getEmail();
    this.idAccount = Number(this.tokenService.getId());
  }

  ngOnInit(): void {
    this.getCustomerByIdAccount();
    this.getListHistoryCart();
  }

  /**
   * Lấy avartar từ sự kiện upload (@Output) => chuyển xuống BE
   */
  onSubmit() {
    this.changeAvatar = new ChangeAvatar(this.form.avatar);
    this.securityService.changeAvatar(this.changeAvatar).subscribe(data => {
      if (JSON.stringify(data) == JSON.stringify(this.error)) {
        this.status = 'Vui lòng upload Avatar!';
      }
      if (JSON.stringify(data) == JSON.stringify(this.success)) {
        this.tokenService.setAvatar(this.form.avatar);
        window.location.reload();
        this.toastrService.success('Thay đổi avatar thành công', 'Thông Báo!', {timeOut: 2000});
      }
    }, error => {
      this.toastrService.error('Up ảnh không thành công!', 'Lỗi', {timeOut: 2000});
    });
  }

  uploadAvatar($event: any) {
    this.form.avatar = $event;
  }

  getCustomerByIdAccount() {
    this.customerService.getCustomerByIdAccount(this.idAccount).subscribe(data => {
      this.customer = data;
    });
  }

  changeInfo() {
    this.checkInfo = true;
    this.checkHistory = false;

  }

  changeHistory() {
    this.checkInfo = false;
    this.checkHistory = true;
  }

  getListHistoryCart() {
    this.cartService.historyPay(this.idAccount).subscribe(data => {
      this.historyCart = data;
      console.log(this.historyCart);
    }, error => {
    }, () => {
    });
  }
}
