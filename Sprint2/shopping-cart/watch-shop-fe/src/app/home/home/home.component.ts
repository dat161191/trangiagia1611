import {Component, Input, OnInit} from '@angular/core';
import {ClockService} from '../../service/clock.service';
import {ClockInfoJson} from '../../clock-info-json';
import {ToastrService} from 'ngx-toastr';
import {ClockHome} from '../../enity/clock/clock-home';
import {BehaviorService} from '../../service/behavior.service';
import {TokenService} from '../../security/service/token.service';
import {CartListByIdAccount} from '../../enity/cart/cart-list-by-id-account';
import {CartService} from '../../service/cart.service';
import {Img} from "../../enity/clock/Img";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  clockList!: ClockInfoJson;
  // request = {page: 0, size: 8};
  // request = {page: 0};
  page = 0;
  search = '';
  clockCarousel: ClockHome[] = [];
  cartListByIdAccount: CartListByIdAccount[] = [];
  idAccount: number = -1;
  size = 4;
  imgList: Img[] = [];


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
    this.getAllList(this.page);
    this.getValueHeader();
    this.idAccount = Number(this.tokenService.getId());
    this.getImgList(this.size);
  }

  getAllList(page: number): void {
    // console.log(page)
    this.clockService.getListClock(page, this.search).subscribe(data => {
      this.clockList = data;
      // console.log(data);
    }, error => {
      this.clockList.content = [];
    });

  }

  changePage(pageNumber: number) {
    // console.log(pageNumber)
    this.page = pageNumber;
    this.getAllList(this.page);
  }

  private getValueHeader() {
    this.behaviorService.getValue().subscribe(data => {
      this.search = data;
      // this.request.page = 0;
      this.page = 0;
      // this.getAllList(this.request);
      this.getAllList(this.page);
    });
  }

  // getListCart() {
  //   this.cartService.getListCartByIdAccount(this.idAccount).subscribe(data => {
  //     this.cartListByIdAccount = data;
  //     this.behaviorService.setCartTotal(String(this.cartListByIdAccount.length));
  //   }, error => {
  //     this.cartListByIdAccount.length = 0;
  //     this.behaviorService.setCartTotal(String(this.cartListByIdAccount.length));
  //   });
  // }
  last: any;
  first: any;

  scroll() {
    window.scrollTo(0, 0);
  }

  /**
   * 03/23/2023
   * @param size
   */
  getImgList(size: any) {
    this.clockService.getListImg(size).subscribe(data => {
      console.log(data)
      this.imgList = data.content;
      this.last = data.last;
      this.first = data.first;
      this.size = data.size;
    })
  }
}
