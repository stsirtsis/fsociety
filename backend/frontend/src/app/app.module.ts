import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { Routing } from './app-routing.module';
import { MainToolbarComponent } from './main-toolbar/main-toolbar.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { FooterComponent } from './footer/footer.component';
import { ParentRegisterPageComponent } from './parent-register-page/parent-register-page.component';
import { ProviderRegisterPageComponent } from './provider-register-page/provider-register-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ParentEventsComponent } from './parent-events/parent-events.component';
import { AnonEventsComponent } from './anon-events/anon-events.component';
import {ParentService} from './parent.service';
import {ProvidersService} from './providers.service';

@NgModule({
  declarations: [
    AppComponent,
    FrontPageComponent,
    MainToolbarComponent,
    FooterComponent,
    ParentRegisterPageComponent,
    ProviderRegisterPageComponent,
    LoginPageComponent,
    ParentEventsComponent,
    AnonEventsComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    Routing,
    FormsModule,
    HttpClientModule
  ],
  providers: [ParentService, ProvidersService],
  bootstrap: [AppComponent]
})
export class AppModule { }
