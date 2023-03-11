import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FacilityType} from "../FacilityType";
import {RentType} from "../RentType";

@Component({
  selector: 'app-furama-create',
  templateUrl: './facility-create.component.html',
  styleUrls: ['./facility-create.component.css']
})
export class FaciltyCreateComponent implements OnInit {
  facilityCreate: FormGroup
  facilityTypes: FacilityType[] = [
    {id: 1, name: "Villa"},
    {id: 2, name: "House"}
    , {id: 3, name: "Room"}
  ]
  renTypes: RentType[] = [
    {id: 1, name: "Year"},
    {id: 2, name: "Month"},
    {id: 3, name: "Date"},
  ]

  constructor() {
    this.facilityCreate = new FormGroup(
      {
        id: new FormControl('', [Validators.required, Validators.pattern('[D][V][-][0-9]{4}')]),
        name: new FormControl('', Validators.required),
        area: new FormControl('', Validators.required),
        cost: new FormControl('', Validators.required),
        maxPeople: new FormControl('', Validators.required),
        facilityType: new FormControl('', Validators.required),
        rentType: new FormControl('', Validators.required),
        standardRoom: new FormControl('', Validators.required),
        descriptionOtherConvenience: new FormControl('', Validators.required),
        poolArea: new FormControl('', Validators.required),
        numberOfFloors: new FormControl('', Validators.required),
        facilityFree: new FormControl('', Validators.required),
      }
    )
  }

  get faci() {
    return this.facilityCreate.controls
  }

  ngOnInit(): void {
  }

  createFacility() {
    console.log(this.facilityCreate.value)
  }
}
