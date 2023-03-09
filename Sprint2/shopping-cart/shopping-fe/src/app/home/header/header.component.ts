import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {TokenService} from '../../security/service/token.service';
import {ClockService} from '../../service/clock.service';
import {BehaviorService} from '../../service/behavior.service';
import {Trademark} from '../../enity/clock/trademark';
import {CartService} from '../../service/cart.service';
import {CartListByIdAccount} from '../../enity/cart/cart-list-by-id-account';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  checkLogin = false;
  name: string | null = '';
  roles: string[] = [];
  avatar: string | null = '';
  idAccount: number = -1;
  trademarks: Trademark[] = [];
  totalProduct = 0;

  constructor(private toast: ToastrService,
              private router: Router,
              private tokenService: TokenService,
              private clockService: ClockService,
              private behaviorService: BehaviorService) {
    this.clockService.getTrademarks().subscribe(data => {
      this.trademarks = data;
    });
  }

  ngOnInit(): void {
    this.behaviorService.getCartTotal().subscribe(data => {
      this.totalProduct = data;
    });
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      this.name = this.tokenService.getName();
      this.roles = this.tokenService.getRole();
      // console.log(this.roles);
      this.avatar = this.tokenService.getAvatar();
      console.log(this.avatar);
      this.idAccount = Number(this.tokenService.getId());
    }

  }

  logOut(): void {
    window.localStorage.clear();
    this.router.navigateByUrl('/').then(() => {
      location.reload();
    });
    this.toast.info('Đăng xuất thành công.', ' Thông báo', {
      timeOut: 3000,
      extendedTimeOut: 1500
    });
  };

  creatClock($event: any) {
// modal create
  }

  searchNameClock(search: any) {
    this.behaviorService.setValue(search);
    this.router.navigateByUrl('');
  }
}
