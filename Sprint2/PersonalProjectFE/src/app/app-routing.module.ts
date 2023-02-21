import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './home/home/home.component';
import {LoginComponent} from './security/login/login.component';
import {InfoComponent} from './info/info/info.component';
import {DetailProductComponent} from './product/detail-product/detail-product.component';
import {CartComponent} from './cart/cart.component';

const routes: Routes = [{path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'info', component: InfoComponent},
  {path: 'detail', component: DetailProductComponent},
  {path: 'cart', component: CartComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
