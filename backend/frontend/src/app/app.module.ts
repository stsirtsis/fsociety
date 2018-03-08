import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {FormsModule} from '@angular/forms';
import {HttpClientModule} from '@angular/common/http';
import {Http, HttpModule} from '@angular/http';
import {AuthConfig, AuthHttp} from 'angular2-jwt';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';


import {AppComponent} from './app.component';
import {Routing} from './app-routing.module';
import {MainToolbarComponent} from './etc/main-toolbar/main-toolbar.component';

import {FrontPageComponent} from './pages/front-page/front-page.component';
import {FooterComponent} from './etc/footer/footer.component';
import {ParentRegisterPageComponent} from './pages/parent-register-page/parent-register-page.component';
import {ProviderRegisterPageComponent} from './pages/provider-register-page/provider-register-page.component';
import {LoginPageComponent} from './pages/login-page/login-page.component';
import {ParentEventsComponent} from './pages/parent-events/parent-events.component';
import {ParentService} from './services/parent.service';
import {ProvidersService} from './services/providers.service';
import {EventService} from './services/event.service';
import {EventsMapComponent} from './pages/parent-events/events-map/events-map.component';
import {AgmCoreModule} from '@agm/core';
import {EventsListComponent} from './pages/parent-events/events-list/events-list.component';
import {SingleEventComponent} from './pages/parent-events/events-list/single-event/single-event.component';
import {EventsMarkerComponent} from './pages/parent-events/events-map/events-marker/events-marker.component';
import {AddEventComponent} from './pages/add-event/add-event.component';
import {OneEventComponent} from './pages/one-event/one-event.component';
import {AuthenticationService} from './services/authentication/authentication.service';
import {UserService} from './services/authentication/user.service';
import {AuthGuardService} from './services/guards/auth-guard.service';
import {AdminAuthGuardService} from './services/guards/admin-auth-guard.service';
import {TOKEN_NAME} from './constants/auth.constants';
import {ProviderGuardService} from './services/guards/provider-guard.service';
import {ParentProfileComponent} from './pages/parent-profile/parent-profile.component';
import {ProviderProfileComponent} from './pages/provider-profile/provider-profile.component';
import {ProvidersHistoryComponent} from './pages/providers-history/providers-history.component';
import {ProviderEventListComponent} from './pages/providers-history/provider-event-list/provider-event-list.component';
import {ProviderSingleEventComponent} from './pages/providers-history/provider-event-list/provider-single-event/provider-single-event.component';
import {ParentHistoryComponent} from './pages/parent-history/parent-history.component';
import {ParentHistoryListComponent} from './pages/parent-history/parent-history-list/parent-history-list.component';
import {ParentSingleEventComponent} from './pages/parent-history/parent-history-list/parent-single-event/parent-single-event.component';
import {UserProfileDataService} from './services/user-profile-data.service';
import { TeamPageComponent } from './pages/team-page/team-page.component';
import {AdminPageComponent} from './pages/admin-page/admin-page.component';
import { AboutPageComponent } from './pages/about-page/about-page.component';
import { ContactPageComponent } from './pages/contact-page/contact-page.component';

export function authHttpServiceFactory(http: Http) {
  return new AuthHttp(new AuthConfig({
    headerPrefix: 'Bearer',
    tokenName: TOKEN_NAME,
    globalHeaders: [{'Content-Type': 'application/json'}],
    noJwtError: false,
    noTokenScheme: true,
    tokenGetter: (() => localStorage.getItem(TOKEN_NAME))
  }), http);
}

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
    SingleEventComponent,
    EventsMarkerComponent,
    AddEventComponent,
    OneEventComponent,
    ParentProfileComponent,
    ProviderProfileComponent,
    ProvidersHistoryComponent,
    ProviderEventListComponent,
    ProviderSingleEventComponent,
    ParentHistoryComponent,
    ParentHistoryListComponent,
    ParentSingleEventComponent,
    TeamPageComponent,
    AdminPageComponent,
    AboutPageComponent,
    ContactPageComponent,
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    Routing,
    FormsModule,
    HttpClientModule,
    HttpModule,
    NgbModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyA9VxQECCNE4WGhC94rFT8ly6QMCLbovYY'
    })
  ],
  providers: [
    {provide: AuthHttp, useFactory: authHttpServiceFactory, deps: [Http]},
    ParentService,
    ProvidersService,
    EventService,
    AuthenticationService,
    UserService,
    AuthGuardService,
    AdminAuthGuardService,
    ProviderGuardService,
    UserProfileDataService
  ],
  bootstrap: [AppComponent]
})

export class AppModule {
}
