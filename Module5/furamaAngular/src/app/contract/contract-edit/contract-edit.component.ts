import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {Employee} from "../../employee/Employee";
import {Customer} from "../../customer/Customer";
import {Facility} from "../../facility/Facility";
import {Contract} from "../Contract";
import {ContractService} from "../contract.service";
import {EmployeeService} from "../../employee/employee.service";
import {CustomerService} from "../../customer/service/customer.service";
import {FacilityService} from "../../facility/service/facility.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-contract-edit',
  templateUrl: './contract-edit.component.html',
  styleUrls: ['./contract-edit.component.css']
})
export class ContractEditComponent implements OnInit {
  contractForm: FormGroup;
  employees: Employee[] = [];
  customers: Customer[] = [];
  facilities: Facility[] = [];
  contract: Contract = {};

  constructor(private contractService: ContractService, private employeeService: EmployeeService,
              private customerService: CustomerService, private facilityService: FacilityService,
              private active: ActivatedRoute, private router: Router) {
    this.employeeService.getAll().subscribe(data => {
      this.employees = data
    });
    this.facilityService.getAll().subscribe(data => {
      this.facilities = data
    });
    this.customerService.getAll().subscribe(data => {
      this.customers = data
    });
    this.contractForm = new FormGroup({
      id: new FormControl(''),
      name: new FormControl('', Validators.required),
      startDate: new FormControl('', Validators.required),
      endDate: new FormControl('', Validators.required),
      deposit: new FormControl('', Validators.required),
      employee: new FormControl('', Validators.required),
      customer: new FormControl('', Validators.required),
      facility: new FormControl('', Validators.required),
    })
    active.paramMap.subscribe(data => {
      const id = data.get('id');
      if (id != null) {
        this.contractService.findById(parseInt(id)).subscribe(data => {
          this.contract = data;
          console.log(data);
          this.contractForm = new FormGroup({
            id: new FormControl(this.contract.id),
            name: new FormControl(this.contract.name, Validators.required),
            startDate: new FormControl(this.contract.startDate, Validators.required),
            endDate: new FormControl(this.contract.endDate, Validators.required),
            deposit: new FormControl(this.contract.deposit, Validators.required),
            employee: new FormControl(this.contract.employee, Validators.required),
            customer: new FormControl(this.contract.customer, Validators.required),
            facility: new FormControl(this.contract.facility, Validators.required),
          })
        })
      }
    })
  }

  get cont() {
    return this.contractForm.controls;
  }

  ngOnInit(): void {
  }

  editContract() {
    this.contract = this.contractForm.value;
    // @ts-ignore
    this.contractService.edit(this.contract.id, this.contract).subscribe(data => {
      this.router.navigateByUrl('/contract/list');
      alert('Edit is success!!!');
    })
  }
}
