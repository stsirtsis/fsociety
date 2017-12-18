import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ParentRegisterPageComponent} from './parent-register-page/parent-register-page.component';
import { ProviderRegisterPageComponent } from './provider-register-page/provider-register-page.component';
import { FrontPageComponent } from './front-page/front-page.component';

const appRoutes: Routes = [
  { path: '', component: FrontPageComponent },
  { path: 'parent-register', component: ParentRegisterPageComponent },
  { path: 'provider-register', component: ProviderRegisterPageComponent }
];

export const Routing = RouterModule.forRoot(appRoutes)
