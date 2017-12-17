import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { routing } from './app-routing.module';
import { FrontPageComponent } from './front-page/front-page.component';
import { ParentRegisterComponent } from './parent-register-component/parent-register-component.component';
import { ProviderRegisterComponent } from './provider-register/provider-register.component';
import { MainToolbarComponent } from './main-toolbar/main-toolbar.component';
import { MatToolbarModule, MatButtonModule, MatCheckboxModule, MatGridListModule, MatInputModule, MatIconModule } from '@angular/material';

@NgModule({
  declarations: [
    AppComponent,
    FrontPageComponent,
    MainToolbarComponent,
    ParentRegisterComponent,
    ProviderRegisterComponent
  ],
  imports: [
    BrowserModule,
    MatButtonModule,
    MatCheckboxModule,
    MatGridListModule,
    MatInputModule,
    MatIconModule,
    MatToolbarModule,
    BrowserAnimationsModule,
    routing
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
