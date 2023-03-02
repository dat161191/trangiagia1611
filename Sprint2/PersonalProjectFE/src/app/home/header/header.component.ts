import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {Router} from '@angular/router';
import {TokenService} from '../../security/service/token.service';
import {ClockHome} from '../../enity/clock/clock-home';
import {ClockService} from '../../service/clock.service';
import {SearchService} from '../../service/search.service';
import {Trademark} from '../../enity/clock/trademark';

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
  idCustomer: any;
  trademarks: Trademark[] = [];

  constructor(private toast: ToastrService,
              private router: Router,
              private tokenService: TokenService,
              private clockService: ClockService,
              private searchService: SearchService,) {
    this.idCustomer = this.tokenService.getId();
    this.clockService.getTrademarks().subscribe(data => {
      this.trademarks = data;
      console.log(this.trademarks);
    });
  }

  ngOnInit(): void {
    if (this.tokenService.getToken()) {
      this.checkLogin = true;
      this.name = this.tokenService.getName();
      this.roles = this.tokenService.getRole();
      // console.log(this.roles);
      this.avatar = this.tokenService.getAvatar();
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

  }

  searchNameClock(search: any) {
    this.searchService.setValue(search);
    this.router.navigateByUrl('');
  }
}
