import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {Providers} from '../../interfaces/providers.interface';
import {ProvidersService} from '../../services/providers.service';
import {Router} from '@angular/router';
import {UserService} from '../../services/authentication/user.service';
import {AuthenticationService} from '../../services/authentication/authentication.service'

@Component({
  selector: 'app-provider-register-page',
  templateUrl: './provider-register-page.component.html',
  styleUrls: ['./provider-register-page.component.css']
})
export class ProviderRegisterPageComponent implements OnInit {

providers: Providers  = { companyName: '', firstName: '', lastName: '', userName: '', password: '',
  description: '', area: '', streetName: '', streetNumber: 0, telNumber: '', mail: '', iban: '', events: []};
error = '';

  constructor(private providerService: ProvidersService, private router: Router,
              private userService: UserService, private auth: AuthenticationService) { }

  ngOnInit() {
    this.userService.logout();
  }

  login() {
    this.auth.login(this.providers.userName, this.providers.password)
      .subscribe(
        result => {
        if (result) {
            this.userService.login(result);
            this.router.navigate(['front-page']);
          } else {
            this.error = 'Username or password is incorrect';
          }
        },
        error => {
          this.error = 'Username or password is incorrect';
        }

      );
  }

  onSubmit() {
    this.providerService.createProvider(this.providers).subscribe(
      value => {
        console.log('[POST] create Provider successfully', value);
      },
      () => {
        console.log('POST Provider - now completed.');
        this.login();
      });

  }

}
