import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {Providers} from '../providers.interface';
import {ProvidersService} from '../providers.service';

@Component({
  selector: 'app-provider-register-page',
  templateUrl: './provider-register-page.component.html',
  styleUrls: ['./provider-register-page.component.css']
})
export class ProviderRegisterPageComponent implements OnInit {

providers: Providers  = { companyName: '', firstName: '', lastName: '', userName: '', password: '', category: '',
  description: '', area: '', streetName: '', streetNumber: 0, telNumber: '', mail: '', iban: '', events: []};

  constructor(private providerService: ProvidersService) { }

  ngOnInit() {
  }
  onSubmit() {
    this.providerService.createProvider(this.providers).subscribe(
      value => {
        console.log('[POST] create Provider successfully', value);
      },
      () => {
        console.log('POST Provider - now completed.');
      });
  }

}
