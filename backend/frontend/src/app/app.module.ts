import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

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
    Routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
