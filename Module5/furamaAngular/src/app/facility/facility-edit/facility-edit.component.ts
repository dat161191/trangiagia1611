import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {FacilityType} from "../FacilityType";
import {RentType} from "../RentType";

@Component({
  selector: 'app-facility-edit',
  templateUrl: './facility-edit.component.html',
  styleUrls: ['./facility-edit.component.css']
})
export class FacilityEditComponent implements OnInit {
  facilityEdit: FormGroup
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
    this.facilityEdit = new FormGroup(
      {
        id: new FormControl(''),
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
    return this.facilityEdit.controls
  }

  ngOnInit(): void {
  }

  editFacility() {
    console.log(this.facilityEdit.value)
  }
}
