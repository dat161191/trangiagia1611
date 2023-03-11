import {Component, OnInit} from '@angular/core';
import {Customer} from "../Customer";
import {CustomerService} from "../service/customer.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-customer-list',
  templateUrl: './customer-list.component.html',
  styleUrls: ['./customer-list.component.css']
})
export class CustomerListComponent implements OnInit {
  customerList: Customer[] = [];
  searchCustomer: FormGroup = new FormGroup({name: new FormControl()});
  temp: Customer = {};
  p: number = 1;

  constructor(private customerService: CustomerService, private router: Router) {
  }

  ngOnInit(): void {
    this.customerService.getAll().subscribe(data => {
      this.customerList = data;
    }, error => {
    }, () => {
    })
  }


  deleteProduct(): void {
    this.customerService.deleteById(this.temp.id).subscribe(data => {
      alert("Delele is Success!!!");
      this.ngOnInit();
    })
  }


  onSearch() {
    console.log(this.searchCustomer.value.name)
    this.customerService.getAll(this.searchCustomer.value.name).subscribe(data => {
      this.customerList = data;
    }, error => {
    }, () => {
    });
  }
}
