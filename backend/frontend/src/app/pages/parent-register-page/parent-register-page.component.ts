import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';
import {AuthenticationService} from '../../services/authentication/authentication.service'

import {Parent} from '../../interfaces/parent.interface';
import {ParentService} from '../../services/parent.service';
import {UserService} from '../../services/authentication/user.service';

@Component({
  selector: 'app-parent-register-page',
  templateUrl: './parent-register-page.component.html',
  styleUrls: ['./parent-register-page.component.css']
})
export class ParentRegisterPageComponent implements OnInit {

  parent: Parent  = { firstName: '', lastName: '', username: '', area:'', streetName:'', streetNumber:0, password: '', email: '', phoneNumber: '', debitCard: '', fpoints: 0};
  error = '';
  error_mail= '';
  constructor(private parentService: ParentService,private router: Router,
    private userService: UserService, private auth: AuthenticationService) {}

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

    onSubmit() {
      if(this.parent.email.indexOf("@") == -1){
        this.error_mail = 'Incorrect email';
      }
      else{
      this.parentService.createParent(this.parent).subscribe(
      value => {
        console.log('[POST] create Parent successfully', value);
      },
      () => {
        console.log('POST Parent - now completed.');
        this.login();
      });
    }

  }
}
