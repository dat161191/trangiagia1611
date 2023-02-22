import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HttpClientModule} from '@angular/common/http';
import {NgxPaginationModule} from 'ngx-pagination';
import { NavbarComponent } from './navbar/navbar.component';
import { FooterComponent } from './footer/footer.component';
import {ReactiveFormsModule} from '@angular/forms';
import { ListConsignment } from './lo-hang/list-consignment/list-consignment';
import { CreateGoodsComponent } from './lo-hang/create-goods/create-goods.component';
import { EditConsignmentComponent } from './lo-hang/edit-consignment/edit-consignment.component';
import { EditLinkComponent } from './lo-hang/edit-link/edit-link.component';
import { CreateLinkComponent } from './lo-hang/create-link/create-link.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    FooterComponent,
    ListConsignment,
    CreateGoodsComponent,
    EditConsignmentComponent,
    EditLinkComponent,
    CreateLinkComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, HttpClientModule, NgxPaginationModule, ReactiveFormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {}

