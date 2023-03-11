import {Component, OnInit} from '@angular/core';
import {Contract} from "../Contract";
import {ContractService} from "../contract.service";
import {Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {Facility} from "../../facility/Facility";
import {FacilityService} from "../../facility/service/facility.service";

@Component({
  selector: 'app-contract-list',
  templateUrl: './contract-list.component.html',
  styleUrls: ['./contract-list.component.css']
})
export class ContractListComponent implements OnInit {
  contractList: Contract[] = [];
  temp: Contract = {};
  searchContract: FormGroup = new FormGroup({id: new FormControl(), name: new FormControl()});
  p: number = 1;

  constructor(private contractService: ContractService, private router: Router,private facilityService:FacilityService) {
  }

  ngOnInit(): void {
    // @ts-ignore
    this.contractService.getAll().subscribe(data => {
      this.contractList = data;
    })
  }


  deleteProduct() {
    this.contractService.deleteById(this.temp.id).subscribe(data => {
      alert("Delele is Success!!!");
      this.ngOnInit();
    })
  }

  onSearch() {
    console.log(this.searchContract.value);
    this.contractService.getAll(this.searchContract.value.name, this.searchContract.value.id).subscribe(data => {
      this.contractList = data;
    }, error => {
    }, () => {
    });
  }
}
