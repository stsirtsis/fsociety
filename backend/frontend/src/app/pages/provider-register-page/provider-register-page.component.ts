import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {Providers} from '../../interfaces/providers.interface';
import {ProvidersService} from '../../services/providers.service';
import {Router} from '@angular/router';
import {UserService} from '../../services/authentication/user.service';
import {AuthenticationService} from '../../services/authentication/authentication.service'
import { CustomResponse } from '../../interfaces/customResponse.interface';

@Component({
  selector: 'app-provider-register-page',
  templateUrl: './provider-register-page.component.html',
  styleUrls: ['./provider-register-page.component.css']
})
export class ProviderRegisterPageComponent implements OnInit {

providers: Providers  = { companyName: '', firstName: '', lastName: '', userName: '', password: '',
  description: '', area: '', streetName: '', streetNumber: 0, telNumber: '', mail: '', iban: '', events: []};
customResponse: CustomResponse;
message: string;
error = '';
error_mail= '';
checkbox = false;

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
            this.router.navigate(['provider-profile']);
          } else {
            this.error = 'Username or password is incorrect';
          }
        },
        error => {
          this.error = 'Username or password is incorrect';
        }

      );
  }
  check(){
    this.checkbox = true;
  }


  onSubmit() {
    if(this.checkbox){
    if(this.providers.mail.indexOf("@") == -1){
      this.error_mail = 'Incorrect email';
    }
    else{
        this.providerService.createProvider(this.providers)
          .subscribe(
          res => {
           this.customResponse = res.body;
           this.message = this.customResponse.message;
           console.log(this.message);
           if(this.message == 'ok with post from provider')
                this.login();
          }),
          () => {
            console.log("in ()");
          }    
    }
  }
  else{
    this.router.navigate(['provider-register']);
  }
  }


}
