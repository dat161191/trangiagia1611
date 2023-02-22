import {Component, OnInit} from '@angular/core';
import {Product} from '../../model/product';
import {Consignment} from '../../model/consignment';
import {ConsignmentService} from '../../service/consignment.service';
import {ProductService} from '../../service/product.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-list-consignment',
  templateUrl: './list-consignment.html',
  styleUrls: ['./list-consignment.component.css']
})
export class ListConsignment implements OnInit {
  consignments: Consignment[] = [];
  products: Product[] = [];
  temp: Consignment = {};
  tempedit: Consignment = {};
  page = 0;
  size = 2;
  totalPage = 0;
  totalElement = 0;


  constructor(private consignmentService: ConsignmentService, private productService: ProductService,
              private router: Router) {
    this.productService.getAll().subscribe(data => {
      this.products = data;
      console.log(this.products);
    });
  }

  ngOnInit(): void {
    this.onSearch('','')
    // this.consignmentService.getAll().subscribe(data => {
    //   this.consignments = data.content;
    //   console.log(this.consignments);
    // });
  }

  delete(): void {
    this.consignmentService.deleteById(this.temp).subscribe(data => {
      alert('Xóa thành công !!!');
      this.ngOnInit();
    });
  }

  creatGoods($event: any) {
    // console.log($event);
    // @ts-ignore
    this.consignmentService.save($event).subscribe(data => {
      alert('Create is success!!!');
      this.ngOnInit();
    });
  }

  onSearch(value: string,value1:string) {
    this.consignmentService.search(value,value1, this.page, this.size).subscribe(data => {
      console.log(data);
      this.consignments = data.content;
      this.totalElement = data.totalElements;
      this.totalPage = data.totalPages;
    });
  }

  editGoods($event: any) {
    this.ngOnInit();
  }

  previousPage() {
    if (this.page == 0) {

    } else {
      this.page = this.page - 1;
      this.ngOnInit();
    }
  }

  nextPage() {
    if (this.page == this.totalPage - 1) {

    } else {
      this.page = this.page + 1;
      this.ngOnInit();
    }
  }
}
