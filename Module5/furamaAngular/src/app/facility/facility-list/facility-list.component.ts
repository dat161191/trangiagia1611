import {Component, OnInit} from '@angular/core';
import {Facility} from "../Facility";
import {FacilityService} from "../service/facility.service";

@Component({
  selector: 'app-facility-list',
  templateUrl: './facility-list.component.html',
  styleUrls: ['./facility-list.component.css']
})
export class FacilityListComponent implements OnInit {
  facilityList: Facility[] = [];
  temp: Facility = {};

  constructor(private facilityService: FacilityService) {
  }

  ngOnInit(): void {
    this.facilityService.getAll().subscribe(data => {
      this.facilityList = data;
    })
  }

  sendId(id: any) {
    this.facilityService.findById(id).subscribe(data => {
      return this.temp = data;
    }, error => {
    }, () => {
    })

  }

  deleteFacility(): void {
    this.facilityService.deleteById(this.temp.id).subscribe(data => {
      this.ngOnInit();
      alert("Delete is Success!!!")
    })
  }
}
