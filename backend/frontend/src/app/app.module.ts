import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { Routing } from './app-routing.module';
import { MainToolbarComponent } from './etc/main-toolbar/main-toolbar.component';
import { FrontPageComponent } from './pages/front-page/front-page.component';
import { FooterComponent } from './etc/footer/footer.component';
import { ParentRegisterPageComponent } from './pages/parent-register-page/parent-register-page.component';
import { ProviderRegisterPageComponent } from './pages/provider-register-page/provider-register-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { ParentEventsComponent } from './pages/parent-events/parent-events.component';
import { ParentService } from './services/parent.service';
import { ProvidersService } from './services/providers.service';
import { EventService } from './services/event.service';
import { EventsMapComponent } from './pages/parent-events/events-map/events-map.component';
import { AgmCoreModule } from '@agm/core';
import { EventsListComponent } from './pages/parent-events/events-list/events-list.component';
import { SingleEventComponent } from './pages/parent-events/events-list/single-event/single-event.component';

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
    EventsMapComponent,
    EventsListComponent,
    SingleEventComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    Routing,
    FormsModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyA9VxQECCNE4WGhC94rFT8ly6QMCLbovYY'
    })
  ],
  providers: [ParentService, ProvidersService, EventService],
  bootstrap: [AppComponent]
})
export class AppModule { }
