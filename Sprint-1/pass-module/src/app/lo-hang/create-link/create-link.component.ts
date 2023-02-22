import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {Product} from '../../model/product';
import {ConsignmentService} from '../../service/consignment.service';
import {ProductService} from '../../service/product.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-create-link',
  templateUrl: './create-link.component.html',
  styleUrls: ['./create-link.component.css']
})
export class CreateLinkComponent implements OnInit {
  consignmentForm: FormGroup;
  products: Product[] = [];

  constructor(private consignmentService: ConsignmentService, private productService: ProductService, private router: Router) {
    this.productService.getAll().subscribe(data => {
      this.products = data;
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

  createConsignment() {
    this.consignmentService.save(this.consignmentForm.value).subscribe(data => {
      this.router.navigateByUrl('/list');
      alert('Create is Success!!!');
    });
  }

  get prod() {
    return this.consignmentForm.controls;
  }
}
