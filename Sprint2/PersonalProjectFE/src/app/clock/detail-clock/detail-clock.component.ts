import {Component, OnInit} from '@angular/core';

const script = document.createElement('script');
script.src = 'assets/jshtml/detail-product.js';
document.body.appendChild(script);
@Component({
  selector: 'app-detail-product',
  templateUrl: './detail-product.component.html',
  styleUrls: ['./detail-product.component.css']
})
export class DetailProductComponent implements OnInit {

  constructor() {
  }

  ngOnInit(): void {
  }

}
