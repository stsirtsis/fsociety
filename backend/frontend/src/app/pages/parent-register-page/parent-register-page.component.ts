import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/authentication/authentication.service'

import {Parent} from '../../interfaces/parent.interface';
import {ParentService} from '../../services/parent.service';
import {UserService} from '../../services/authentication/user.service';
import { CustomResponse } from '../../interfaces/customResponse.interface';

@Component({
  selector: 'app-parent-register-page',
  templateUrl: './parent-register-page.component.html',
  styleUrls: ['./parent-register-page.component.css']
})
export class ParentRegisterPageComponent implements OnInit {

  parent: Parent  = { firstName: '', lastName: '', username: '', area:'', streetName:'', streetNumber:0, password: '', email: '', phoneNumber: '', debitCard: '', fpoints: 0};
  message: string;
  customResponse: CustomResponse
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
  a=1;
  constructor(private parentService: ParentService,private router: Router,
    private userService: UserService, private auth: AuthenticationService,
    ) {}

    ngOnInit(): void {
      this.userService.logout();
    }

    login() {
      this.auth.login(this.parent.username, this.parent.password)
        .subscribe(
          result => {
          if (result) {
              this.userService.login(result);
              this.router.navigate(['parent-events']);
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
    this.error_tel='' ;
    this.error_iban='';
    this.error_fir='';
    this.error_las='';
    this.error_user='';
    this.error_pass='';
    this.a=1;
    if (this.parent.firstName == '')
    {
      this.error_fir="Field first name must be complected";
      this.a=0;
    }
    if (this.parent.lastName == '')
    {
      this.error_las="Field last name must be complected";
      this.a=0;
    }
    if (this.parent.username == '')
    {
      this.error_user="Field usernane must be complected";
      this.a=0;
    }
    if (this.parent.password.length <8)
    {
      this.error_pass="Password must be at least 8 characters";
      this.a=0;
    }

    if(this.parent.email.indexOf("@") == -1){
      this.error_mail = 'Incorrect email';
      this.a=0;
    }
    if (this.parent.phoneNumber.length != 10)
    {
      this.error_tel= 'Incorrect Telephone Number (10 digits needed)';
      this.a=0;
    }
    if (this.parent.debitCard.length != 16)
    {
      this.error_iban= 'Incorrect Iban (16 digits needed)';
      this.a=0;
    }
       else if (this.a==1){
         this.parentService.createParent(this.parent)
             .subscribe(
             res => {
              if(this.parent.email.indexOf("@") == -1){
                   this.error_mail = 'Incorrect email';
              }
              else {
                   this.customResponse = res.body;
                   this.message = this.customResponse.message;
                   console.log(this.message);
                   if(this.message == 'ok with post from parent')
                       this.login();
              }
             }),
             () => {
               console.log("in ()");
             }
        }

   }
   else{
     this.error_ch= 'Terms of use must be accepted';
     this.router.navigate(['parent-register']);
     this.a=0;
   }
   }

 }
