import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Employee} from "../../employee/Employee";
import {Facility} from "../../facility/Facility";
import {Customer} from "../../customer/Customer";
import {Contract} from "../Contract";
import {ContractService} from "../contract.service";
import {EmployeeService} from "../../employee/employee.service";
import {CustomerService} from "../../customer/service/customer.service";
import {FacilityService} from "../../facility/service/facility.service";

@Component({
  selector: 'app-contract-create',
  templateUrl: './contract-create.component.html',
  styleUrls: ['./contract-create.component.css']
})
export class ContractCreateComponent implements OnInit {
  contractForm: FormGroup;
  employees: Employee[] = [];
  customers: Customer[] = [];
  facilities: Facility[] = [];
  contract: Contract = {};

  constructor(private contractService: ContractService, private employeeService: EmployeeService,
              private customerService: CustomerService, private facilityService: FacilityService) {
    this.employeeService.getAll().subscribe(data => {
      this.employees = data
    });
    this.customerService.getAll().subscribe(data => {
      this.customers = data
    });
    this.facilityService.getAll().subscribe(data => {
      this.facilities = data
    });
    this.contractForm = new FormGroup({
      name: new FormControl('', Validators.required),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('', Validators.required),
      deposit: new FormControl('', Validators.required),
      employee: new FormControl('', Validators.required),
      customer: new FormControl('', Validators.required),
      facility: new FormControl('', Validators.required),
    })
    console.log(this.contractForm);
  }

  get cont() {
    return this.contractForm.controls;
  }

  ngOnInit(): void {
  }

  createContract() {
    console.log(this.contractForm.value);
    this.contract = this.contractForm.value;
    this.contractService.save(this.contract).subscribe(data => {
      this.contractForm.reset();
      alert("Create is success!!!");
    });
  }
}
