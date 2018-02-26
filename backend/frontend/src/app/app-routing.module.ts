import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ParentRegisterPageComponent} from './pages/parent-register-page/parent-register-page.component';
import { ProviderRegisterPageComponent } from './pages/provider-register-page/provider-register-page.component';
import { FrontPageComponent } from './pages/front-page/front-page.component';
import { LoginPageComponent } from './pages/login-page/login-page.component';
import { ParentEventsComponent} from './pages/parent-events/parent-events.component';
import { EventsMapComponent } from './pages/parent-events/events-map/events-map.component';
import { OneEventComponent } from './pages/one-event/one-event.component';
import { AddEventComponent } from './pages/add-event/add-event.component';
import { AuthGuardService } from './services/guards/auth-guard.service';
import { AdminAuthGuardService } from './services/guards/admin-auth-guard.service';
import { ProviderGuardService} from './services/guards/provider-guard.service';
import { ProviderProfileComponent} from './pages/provider-profile/provider-profile.component';
import { ProvidersHistoryComponent} from './pages/providers-history/providers-history.component';
import { ParentProfileComponent } from './pages/parent-profile/parent-profile.component';
import { ParentHistoryComponent } from './pages/parent-history/parent-history.component';

const appRoutes: Routes = [
  {
    path: '',
    component: FrontPageComponent
  },
  {
    path: 'parent-register',
    component: ParentRegisterPageComponent
  },
  {
    path: 'parent-history',
    component: ParentHistoryComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'parent-profile',
    component: ParentProfileComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'provider-profile',
    component: ProviderProfileComponent,
    canActivate: [ProviderGuardService]
  },
  {
    path: 'providers-history',
    component: ProvidersHistoryComponent,
    canActivate: [ProviderGuardService]
  },
  {
    path: 'provider-register',
    component: ProviderRegisterPageComponent
  },
  {
    path: 'login-page',
    component: LoginPageComponent
  },
  {
    path: 'parent-events',
    component: ParentEventsComponent
  },
  {
    path: 'map',
    component: EventsMapComponent
  },
  {
    path: 'one-event/:Id' ,
    component: OneEventComponent,
    canActivate: [AuthGuardService]
  },
  {
    path: 'add-event',
    component: AddEventComponent,
    canActivate: [ProviderGuardService]
  },
  {
    path: '**',
    redirectTo : ''
  }
];

export const Routing = RouterModule.forRoot(appRoutes);
