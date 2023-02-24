import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './home/home/home.component';
import {LoginComponent} from './security/login/login.component';
import {InfoComponent} from './info/info/info.component';
import {DetailClockComponent} from './clock/detail-clock/detail-clock.component';
import {CartComponent} from './cart/cart.component';
import {RegisterComponent} from './security/register/register.component';

const routes: Routes = [{path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'info', component: InfoComponent},
  {path: 'detail/:id', component: DetailClockComponent},
  {path: 'cart', component: CartComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
