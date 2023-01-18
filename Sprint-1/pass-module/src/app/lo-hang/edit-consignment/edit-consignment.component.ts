import {Component, Input, EventEmitter, OnChanges, OnInit, Output, SimpleChanges} from '@angular/core';
import {Consignment} from '../../model/consignment';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ConsignmentService} from '../../service/consignment.service';
import {ProductService} from '../../service/product.service';
import {Router} from '@angular/router';
import {Product} from '../../model/product';

@Component({
  selector: 'app-edit-consignment',
  templateUrl: './edit-consignment.component.html',
  styleUrls: ['./edit-consignment.component.css']
})
export class EditConsignmentComponent implements OnInit, OnChanges {
  @Input() consignment: Consignment = {};
  products: Product[] = [];
  consignmentForm: FormGroup;
  @Output()
  emitEdit = new EventEmitter();

  constructor(private consignmentService: ConsignmentService, private productService: ProductService, private router: Router) {
    this.productService.getAll().subscribe(data => {
      this.products = data;
      console.log(data);
    });
    this.consignmentForm = new FormGroup({
      id: new FormControl(''),
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

  editConsignment() {
    const consig = this.consignmentForm.value;
    this.consignmentService.update(consig).subscribe(data => {
      alert('Edit is success!!!');
      this.emitEdit.emit('');
    });

  }

  compareWith(o1: Product, o2: Product): boolean {
    return o1 && o2 ? o1.proId === o2.proId : o1 === o2;
  }

  ngOnChanges(changes: SimpleChanges): void {
    console.log(this.consignment);
    this.consignmentForm.patchValue(this.consignment);
    console.log(this.consignmentForm.value);
  }
}
