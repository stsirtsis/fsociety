import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';


import { AppComponent } from './app.component';
import { routing } from './app-routing.module';
import { ParentRegisterComponent } from './parent-register-component/parent-register-component.component';
import { ProviderRegisterComponent } from './provider-register/provider-register.component';


@NgModule({
  declarations: [
    AppComponent,
    ParentRegisterComponent,
    ProviderRegisterComponent
  ],
  imports: [
    BrowserModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
