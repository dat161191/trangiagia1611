import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {ListConsignment} from './lo-hang/list-consignment/list-consignment';
import {CreateGoodsComponent} from './lo-hang/create-goods/create-goods.component';

const routes: Routes = [
    {path: 'list', component: ListConsignment}]
;

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
