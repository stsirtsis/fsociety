import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { ParentRegisterComponent} from './parent-register-component/parent-register-component.component';
import { ProviderRegisterComponent } from './provider-register/provider-register.component';
    
const appRoutes: Routes = [
  { path: 'parent_register', component: ParentRegisterComponent },
  { path: 'provider_register', component: ProviderRegisterComponent }    
];
    
export const routing = RouterModule.forRoot(appRoutes)
