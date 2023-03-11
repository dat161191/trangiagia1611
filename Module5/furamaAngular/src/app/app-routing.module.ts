import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CustomerListComponent} from "./customer/customer-list/customer-list.component";
import {CustomerCreateComponent} from "./customer/customer-create/customer-create.component";
import {CustomerEditComponent} from "./customer/customer-edit/customer-edit.component";
import {FacilityListComponent} from "./facility/facility-list/facility-list.component";
import {FaciltyCreateComponent} from "./facility/facility-create/facilty-create.component";
import {FacilityEditComponent} from "./facility/facility-edit/facility-edit.component";
import {ContractListComponent} from "./contract/contract-list/contract-list.component";
import {ContractCreateComponent} from "./contract/contract-create/contract-create.component";
import {HomeComponent} from "./home/home/home.component";
import {ContractEditComponent} from "./contract/contract-edit/contract-edit.component";


const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'customer/list', component: CustomerListComponent},
  {path: 'customer/create', component: CustomerCreateComponent},
  {path: 'customer/edit/:id', component: CustomerEditComponent},
  {path: 'facility/list', component: FacilityListComponent},
  {path: 'facility/create', component: FaciltyCreateComponent},
  {path: 'facility/edit/:id', component: FacilityEditComponent},
  {path: 'contract/list', component: ContractListComponent},
  {path: 'contract/create', component: ContractCreateComponent},
  {path: 'contract/edit/:id', component: ContractEditComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
