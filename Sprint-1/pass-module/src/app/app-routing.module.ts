import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ListConsignment} from './lo-hang/list-consignment/list-consignment';
import {CreateGoodsComponent} from './lo-hang/create-goods/create-goods.component';
import {EditLinkComponent} from './lo-hang/edit-link/edit-link.component';
import {CreateLinkComponent} from './lo-hang/create-link/create-link.component';

const routes: Routes = [
    {path: 'list', component: ListConsignment},
    {path: 'edit/:id', component: EditLinkComponent},
    {path: 'create', component: CreateLinkComponent},

  ]
;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
