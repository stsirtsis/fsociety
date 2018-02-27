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
        this.parentService.createParent(this.parent)
          .subscribe(
          res => {
           this.customResponse = res.body;
           this.message = this.customResponse.message;
           console.log(this.message);
           if(this.message == 'ok with post from parent')
                this.login();
          }),
          () => {
            console.log("in ()");
          }
      }
    
  }
