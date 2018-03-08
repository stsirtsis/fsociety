import {Component, OnInit} from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {AuthenticationService} from '../../services/authentication/authentication.service';
import {UserService} from '../../services/authentication/user.service';
import {ParentService} from '../../services/parent.service';
import {CustomResponse} from '../../interfaces/customResponse.interface';
import {UserProfileDataService} from '../../services/user-profile-data.service';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  model: any = {};
  temp_model: any={};
  loading = false;
  error = '';
  redirectUrl: string;
  blocked = false;
  message= '';
  customResponse : CustomResponse;
  error1= '';
  success= '';

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private authenticationService: AuthenticationService,
              private userService: UserService,
              private parentService: ParentService,
              private userProfileDataService: UserProfileDataService) {
    this.redirectUrl = this.activatedRoute.snapshot.queryParams['redirectTo'];
  }

  ngOnInit(): void {
    this.userService.logout();
  }

  login() {
    this.loading = true;

    this.authenticationService.login(this.model.username, this.model.password)
      .subscribe(
        result => {
          this.loading = false;

          if (result) {
            if (!this.userService.login(result)) {
              this.blocked = true;
              return;
            }

            this.navigateAfterSuccess();
          } else {
            this.error = 'Λανθασμένο όνομα ή κωδικός χρήστη';
          }
        },
        error => {
          this.error = 'Λανθασμένο όνομα ή κωδικός χρήστη';
          this.loading = false;
        }
      );
  }

  private navigateAfterSuccess() {
    if (this.userService.isParentUser()) {
      this.router.navigate(['parent-events']);
    }
    if (this.userService.isProviderUser()) {
      this.router.navigate(['provider-profile']);
    }
    if (this.userService.isAdminUser()){
      this.router.navigate(['admin-page']);
    }

  }

  onSubmit(){

      this.userProfileDataService.send_email(this.temp_model.username)
        .subscribe(
          res => {
            this.customResponse = res.body;
            this.message = this.customResponse.message;
            console.log(this.message);
            if (this.message === 'Your password has been reset.Please check your emails.') {
              this.success= 'Ο κωδικός στάλθηκε με επιτυχία στο email σας';
            }
            else{
              this.error1 = 'Το όνομα χρήστη δεν βρέθηκε';
            }
          },
          error => {
            console.log(error);
          }
        );

    }
}
