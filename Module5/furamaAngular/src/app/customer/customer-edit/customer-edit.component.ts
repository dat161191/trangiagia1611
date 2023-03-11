import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerType} from "../CustomerType";
import {CustomerService} from "../service/customer.service";
import {CustomerTypeService} from "../service/customer-type.service";
import {ActivatedRoute, Router} from "@angular/router";
import {differenceInYears} from 'date-fns';

@Component({
  selector: 'app-customer-edit',
  templateUrl: './customer-edit.component.html',
  styleUrls: ['./customer-edit.component.css']
})
export class CustomerEditComponent implements OnInit {
  formCustomerEdit: FormGroup;
  customerTypeList: CustomerType[] = [];
  // id: number | undefined;
  // customer: Customer | null = {};
  constructor(private customerService: CustomerService, private customerTypeService: CustomerTypeService,
              private active: ActivatedRoute, private router: Router) {
    this.customerTypeService.getAll().subscribe(data => {
      this.customerTypeList = data;
    })
    this.formCustomerEdit = new FormGroup({
      id: new FormControl(''),
      customerCode: new FormControl('', [Validators.required, Validators.pattern('[K][H][-][0-9]{4}')]),
      customerType: new FormControl('', Validators.required),
      name: new FormControl('', [Validators.required, Validators.pattern("([A-Z][a-z]+[ ])+([A-Z][a-z]+)")]),
      // dateOfBirth: new FormControl('', [Validators.required,this.validateBirthday]),
      dateOfBirth: new FormControl('', Validators.required),
      gender: new FormControl('', Validators.required),
      idCard: new FormControl('', [Validators.required, Validators.pattern("[0-9]{9}")]),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern('([0][9][0][0-9]{7})|([+][8][4][9][0][0-9]{7})')]),
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      address: new FormControl('', Validators.required)
    })
    console.log(this.formCustomerEdit.value);
    this.active.paramMap.subscribe(data => {
      // @ts-ignore
      const id = +data.get('id');
      if (id != null) {
        this.getCustomer(id);
      }
    })
  }

  ngOnInit(): void {
  }

  get cus() {
    return this.formCustomerEdit.controls
  }

  editCustomer(): any {
    this.customerService.editCustomer(this.formCustomerEdit.value).subscribe(data=>{
      this.router.navigateByUrl('/customer/list');
      alert('Edit is success!!! ')
    })
  }

  compareWith(o1: CustomerType, o2: CustomerType): boolean {
    return o1 && o2 ? o1.id === o2.id : o1 === o2;
  }

  getCustomer(id: number): void {
    this.customerService.findById(id).subscribe(data => {
      this.formCustomerEdit.patchValue(data);
      console.log(this.formCustomerEdit.value);
    })
  }
  // validateBirthday(c: AbstractControl): any {
  //   const date = new Date(c.value);
  //   const age = differenceInYears(new Date(), date);
  //   return (age <= 18) ? {greaterThan18: true} : null;
  // }
}
