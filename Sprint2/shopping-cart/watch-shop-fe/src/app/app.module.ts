import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './security/login/login.component';
import {ToastrModule} from 'ngx-toastr';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {HomeComponent} from './home/home/home.component';
import {FooterComponent} from './home/footer/footer.component';
import {HeaderComponent} from './home/header/header.component';
import {AuthInterceptor} from './security/service/auth.interceptor';
import {InfoComponent} from './info/info.component';
import {CartComponent} from './cart/cart.component';
import {ClockCreateComponent} from './clock/create-clock/clock-create.component';
import {DetailClockComponent} from './clock/detail-clock/detail-clock.component';
import {CarouselModule} from 'ngx-owl-carousel-o';
import {ClockEditComponent} from './clock/clock-edit/clock-edit.component';
import {RegisterComponent} from './security/register/register.component';
import {PersonalPageComponent} from './personal-page/personal-page.component';
import {StatisticalComponent} from './clock/statistical/statistical.component';
import {PayClockComponent} from './pay-clock/pay-clock.component';
import {AngularFireStorageModule} from '@angular/fire/storage';
import {AngularFireModule} from '@angular/fire';
import {environment} from '../environments/environment.prod';
import {UploadAvatarComponent} from './upload/upload-avatar/upload-avatar.component';
import {PhonePipe} from './pipes/phone-pipe';
import {CCCDPipe} from './pipes/CCCD-Pipe';
import {NgxPaginationModule} from 'ngx-pagination';
import { HomeSeeMoreComponent } from './home/home-see-more/home-see-more.component';


@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
    InfoComponent,
    CartComponent,
    ClockCreateComponent,
    ClockEditComponent,
    DetailClockComponent,
    RegisterComponent,
    PersonalPageComponent,
    StatisticalComponent,
    PayClockComponent,
    UploadAvatarComponent,
    PhonePipe,CCCDPipe, HomeSeeMoreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule, CarouselModule, FormsModule,
    AngularFireStorageModule, AngularFireModule.initializeApp(environment.firebaseConfig), NgxPaginationModule
  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: AuthInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
