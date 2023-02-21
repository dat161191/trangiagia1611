import {Component, OnInit} from '@angular/core';
import {ProductHome} from '../../enity/product/product-home';
import {ProductService} from '../../service/product.service';
import {ProductInfoJson} from '../../product-info-json';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  productList!: ProductInfoJson;
  request = {page: 0, size: 5};
  pageNumber = 0;

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    this.getAllList(this.request);

  }

  private getAllList(request: { page: number; size: number } | undefined): void {
    this.productService.getListProduct(request).subscribe(data => {
      this.productList = data;
    });

  }
}
