import { Component, OnInit } from '@angular/core';
import {Router, ActivatedRoute} from '@angular/router';

import {AuthenticationService} from '../../services/authentication/authentication.service';
import {UserService} from '../../services/authentication/user.service';



@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent implements OnInit {
  model: any = {};
  loading = false;
  error = '';
  redirectUrl: string;

  constructor(private router: Router,
              private activatedRoute: ActivatedRoute,
              private authenticationService: AuthenticationService,
              private userService: UserService) {
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
            this.userService.login(result);
            this.navigateAfterSuccess();
          } else {
            this.error = 'Username or password is incorrect';
          }
        },
          error => {
            this.error = 'Username or password is incorrect';
            this.loading = false;
          }

      );
  }

  private navigateAfterSuccess() {
    this.router.navigate(['parent-events']);
  }
}
