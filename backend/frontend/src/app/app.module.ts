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

@NgModule({
  declarations: [
    AppComponent,
    FrontPageComponent,
    MainToolbarComponent,
    FooterComponent,
    ParentRegisterPageComponent,
    ProviderRegisterPageComponent
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
