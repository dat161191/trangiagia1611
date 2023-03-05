import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomeComponent} from './home/home/home.component';
import {LoginComponent} from './security/login/login.component';
import {InfoComponent} from './info/info.component';
import {DetailClockComponent} from './clock/detail-clock/detail-clock.component';
import {CartComponent} from './cart/cart.component';
import {RegisterComponent} from './security/register/register.component';
import {AuthGuard} from './canactive/auth.guard';
import {AdminGuard} from './canactive/admin.guard';
import {StatisticalComponent} from './clock/statistical/statistical.component';
import {PersonalPageComponent} from './personal-page/personal-page.component';
import {ClockCreateComponent} from './clock/create-clock/clock-create.component';
import {UserGuard} from './canactive/user.guard';

const routes: Routes = [{path: '', component: HomeComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'info', component: InfoComponent},
  {path: 'detail/:id', component: DetailClockComponent},
  {path: 'cart/:id', component: CartComponent, canActivate: [AuthGuard,UserGuard]},
  {path: 'personal-page', component: PersonalPageComponent, canActivate: [AuthGuard]},
  {path: 'statistical', component: StatisticalComponent, canActivate: [AuthGuard, AdminGuard]},
  {path: 'create', component: ClockCreateComponent, canActivate: [AuthGuard, AdminGuard]},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
