import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MaterialModule } from './material.module'

import { AppComponent } from './app.component';
import { routing } from './app-routing.module';
import { MainToolbarComponent } from './main-toolbar/main-toolbar.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { ParentRegisterComponent } from './parent-register-component/parent-register-component.component';
import { ProviderRegisterComponent } from './provider-register/provider-register.component';
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [
    AppComponent,
    FrontPageComponent,
    MainToolbarComponent,
    ParentRegisterComponent,
    ProviderRegisterComponent,
    FooterComponent
  ],
  imports: [
    BrowserModule,
    MaterialModule,
    BrowserAnimationsModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
