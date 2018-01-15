import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ParentRegisterPageComponent} from './parent-register-page/parent-register-page.component';
import { ProviderRegisterPageComponent } from './provider-register-page/provider-register-page.component';
import { FrontPageComponent } from './front-page/front-page.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ParentEventsComponent} from './parent-events/parent-events.component';
import { AnonEventsComponent} from './anon-events/anon-events.component';
import { EventsMapComponent } from './parent-events/events-map/events-map.component';

const appRoutes: Routes = [
  { path: '', component: FrontPageComponent },
  { path: 'parent-register', component: ParentRegisterPageComponent },
  { path: 'provider-register', component: ProviderRegisterPageComponent },
  { path: 'login-page' ,  component: LoginPageComponent},
  { path: 'parent-events', component: ParentEventsComponent},
  { path: 'anon-events', component: AnonEventsComponent},
  { path: 'map', component: EventsMapComponent}
];

export const Routing = RouterModule.forRoot(appRoutes);
