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
error_tel='';
error_iban='';
error_ch='';
error_nul='';
checkbox = false;
error_fir='';
error_las='';
error_user='';
error_pass='';
error_prov='';
error_desc='';
a=1;

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
    this.error_ch='';
   if(this.checkbox){
   this.error_mail='';
   this.error_prov='';
   this.error_tel='' ;
   this.error_iban='';
   this.error_fir='';
   this.error_las='';
   this.error_user='';
   this.error_pass='';
   this.error_desc='';
   this.a=1;

   if (this.providers.companyName == '')
   {
     this.error_prov="Field Company name must be complected";
     this.a=0;
   }

    if (this.providers.description == '')
    {
      this.error_desc="Field Description must be complected";
      this.a=0;
    }
   if (this.providers.firstName == '')
   {
     this.error_fir="Field first name must be complected";
     this.a=0;
   }
   if (this.providers.lastName == '')
   {
     this.error_las="Field last name must be complected";
     this.a=0;
   }
   if (this.providers.userName == '')
   {
     this.error_user="Field usernane must be complected";
     this.a=0;
   }
   if (this.providers.password.length <8)
   {
     this.error_pass="Password must be at least 8 characters";
     this.a=0;
   }

   if(this.providers.mail.indexOf("@") == -1){
     this.error_mail = 'Incorrect email';
     this.a=0;
   }
   if (this.providers.telNumber.length != 10)
   {
     this.error_tel= 'Incorrect Telephone Number (10 digits needed)';
     this.a=0;
   }
   if (this.providers.iban.length != 16)
   {
     this.error_iban= 'Incorrect Iban (16 digits needed)';
     this.a=0;
   }
   else if (this.a==1){
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
    this.error_ch= 'Terms of use must be accepted';
   this.router.navigate(['provider-register']);
   this.a=0;
  }
  }


}
