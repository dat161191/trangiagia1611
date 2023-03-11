import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {CustomerType} from "../CustomerType";
import {CustomerService} from "../service/customer.service";
import {CustomerTypeService} from "../service/customer-type.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {
  formCustomerCreate: FormGroup;
  customerTypeList: CustomerType[] = [];

  constructor(private customerService: CustomerService, private customerTypeService: CustomerTypeService, private router: Router) {
    this.customerTypeService.getAll().subscribe(data => {
      this.customerTypeList = data;
    })
    console.log(this.customerTypeList);
    this.formCustomerCreate = new FormGroup({
      customerCode: new FormControl('', [Validators.required, Validators.pattern("[K][H][-][0-9]{4}")]),
      customerType: new FormControl('', Validators.required),
      name: new FormControl('', [Validators.required, Validators.pattern("([A-Z][a-z]+[ ])+([A-Z][a-z]+)")]),
      dateOfBirth: new FormControl('', Validators.required),
      gender: new FormControl('', Validators.required),
      idCard: new FormControl('', [Validators.required, Validators.pattern("[0-9]{9}")]),
      phoneNumber: new FormControl('', [Validators.required, Validators.pattern('(((\\\\+|)84)|0)(3|5|7|8|9)+([0-9]{8})')]),
      email: new FormControl('', [Validators.required, Validators.pattern("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,4}$")]),
      address: new FormControl('', Validators.required)
    })
  }

  get cus() {
    return this.formCustomerCreate.controls
  }

  ngOnInit(): void {
  }

  createCustomer() {
    const customer = this.formCustomerCreate.value;
    this.customerService.saveCustomer(customer).subscribe(() => {
      this.formCustomerCreate.reset();
      alert('Create is Success!!!');
      console.log(customer)
    })
  }
}
