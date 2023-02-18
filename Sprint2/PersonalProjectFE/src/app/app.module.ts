import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {LoginComponent} from './security/login/login.component';
import {ToastrModule} from 'ngx-toastr';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ReactiveFormsModule} from '@angular/forms';
import {HomeComponent} from './home/home/home.component';
import {FooterComponent} from './home/footer/footer.component';
import {HeaderComponent} from './home/header/header.component';
import {AuthInterceptor} from './security/service/auth.interceptor';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    FooterComponent,
    HeaderComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ToastrModule.forRoot(),
    HttpClientModule,
    BrowserAnimationsModule,
    ReactiveFormsModule,
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