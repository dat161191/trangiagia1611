import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Product} from '../../model/product';
import {ConsignmentService} from '../../service/consignment.service';
import {ProductService} from '../../service/product.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-goods',
  templateUrl: './create-goods.component.html',
  styleUrls: ['./create-goods.component.css']
})
export class CreateGoodsComponent implements OnInit {
  consignmentForm: FormGroup;
  products: Product[] = [];
  @Output()
  emit = new EventEmitter();

  constructor(private consignmentService: ConsignmentService, private productService: ProductService, private router: Router) {
    this.productService.getAll().subscribe(data => {
      this.products = data;
      console.log(data);
    });

    this.consignmentForm = new FormGroup({
      consignmentCode: new FormControl('', [Validators.required, Validators.pattern('[L][X][-][0-9]{4}')]),
      quantity: new FormControl('', [Validators.required, Validators.min(0)]),
      importDate: new FormControl('', [Validators.required]),
      productionDate: new FormControl('', [Validators.required]),
      expirationDate: new FormControl('', [Validators.required]),
      product: new FormControl('', [Validators.required]),
    });
  }

  ngOnInit(): void {
  }

  get prod() {
    return this.consignmentForm.controls;
  }

  createConsignment(): void {
    const consignment = this.consignmentForm.value;
    console.log(consignment);
    this.emit.emit(consignment);
    // this.consignmentService.save(consignment).subscribe(data => {
    //   this.router.navigateByUrl('/list');
    //   alert('Create success !!!');
    // });
  }
}
