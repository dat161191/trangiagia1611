import {Component, OnInit} from '@angular/core';

// declare var myFunction: any;
const script = document.createElement('script');
script.src = 'assets/JS/detail.js';
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
