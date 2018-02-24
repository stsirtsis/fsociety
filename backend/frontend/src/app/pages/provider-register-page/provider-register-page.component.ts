import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {Providers} from '../../interfaces/providers.interface';
import {ProvidersService} from '../../services/providers.service';
import {Router} from '@angular/router';
import {UserService} from '../../services/authentication/user.service';

@Component({
  selector: 'app-provider-register-page',
  templateUrl: './provider-register-page.component.html',
  styleUrls: ['./provider-register-page.component.css']
})
export class ProviderRegisterPageComponent implements OnInit {

providers: Providers  = { companyName: '', firstName: '', lastName: '', userName: '', password: '', category: '',
  description: '', area: '', streetName: '', streetNumber: 0, telNumber: '', mail: '', iban: '', events: []};
error = '';
  constructor(private providerService: ProvidersService, private router: Router,
              private userService: UserService) { }

  ngOnInit() {
  }
  onSubmit() {
    //if()
  //this.error = 'username already exists';
    this.providerService.createProvider(this.providers).subscribe(
      value => {
        console.log('[POST] create Provider successfully', value);
      },
      () => {
        console.log('POST Provider - now completed.');
      });
      this.userService.isProvider = true;
      this.userService.username = this.providers.userName;
      this.router.navigate(['front-page']);
  }

}
