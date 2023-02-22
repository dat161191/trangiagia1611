import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidatorFn, Validators} from '@angular/forms';
import {ConsignmentService} from '../../service/consignment.service';
import {ProductService} from '../../service/product.service';
import {ActivatedRoute, Route, Router} from '@angular/router';
import {Product} from '../../model/product';

@Component({
  selector: 'app-edit-link',
  templateUrl: './edit-link.component.html',
  styleUrls: ['./edit-link.component.css']
})
export class EditLinkComponent implements OnInit {
  consignmentForm: FormGroup;
  products: Product[] = [];
  // listCode: ListCode[] | undefined;

  constructor(private consignmentService: ConsignmentService, private productService: ProductService,
              private active: ActivatedRoute, private router: Router) {
    this.productService.getAll().subscribe(data => {
      this.products = data;
    });
    this.consignmentForm = new FormGroup({
      id: new FormControl(''),
      consignmentCode: new FormControl('', [Validators.required, Validators.pattern('[L][X][-][0-9]{4}')]),
      quantity: new FormControl('', [Validators.required, Validators.min(0)]),
      importDate: new FormControl('', [Validators.required]),
      productionDate: new FormControl('', [Validators.required]),
      expirationDate: new FormControl('', [Validators.required]),
      product: new FormControl('', [Validators.required]),
    }, {validators: [this.checkCode]});
    this.active.paramMap.subscribe(data => {
      // @ts-ignore
      const id = +data.get('id');
      if (id != null) {
        this.consignmentService.findById(id).subscribe(data => {
          // console.log(data);
          this.consignmentForm.patchValue(data);
        });
      }
    });
  }

  ngOnInit(): void {
  }

  get prod() {
    return this.consignmentForm.controls;
  }

  compareWith(o1: Product, o2: Product): boolean {
    return o1 && o2 ? o1.proId === o2.proId : o1 === o2;
  }

  editConsignment() {
    this.consignmentService.update(this.consignmentForm.value).subscribe(data =>
      this.router.navigateByUrl('/list'));
    alert('Edit is success!!!');
  }

  checkCode: ValidatorFn = (control: AbstractControl): { checkCode: boolean } => {
    // @ts-ignore
    const code = control.get('consignmentCode')?.value;
    let result = {checkCode: false};
    // @ts-ignore
    this.listCode?.forEach(value => {
      if (code === value.usernameAccount) {
        result = {checkCode: true};
      }
    });
    return result;
  };
}
