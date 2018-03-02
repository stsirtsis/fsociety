import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/authentication/user.service';
import {UserProfileDataService} from '../../services/user-profile-data.service';
import {Providers} from '../../interfaces/providers.interface';

@Component({
  selector: 'app-provider-profile',
  templateUrl: './provider-profile.component.html',
  styleUrls: ['./provider-profile.component.css']
})
export class ProviderProfileComponent implements OnInit {
  username: string;
  access_token: string;
  provider = new Providers();
  error_message: string;

  constructor(private userService: UserService, private userProfileDataService: UserProfileDataService) {
  }

  ngOnInit() {
    this.username = this.userService.getUsername();
    this.access_token = this.userService.getAccessToken();

    this.userProfileDataService.getUserData(this.username, this.access_token)
      .subscribe(
        res => {
          const body = res.body.message;

          if (this.userService.isProvider) {
            if (typeof body === 'string')
              this.error_message = 'username not found';
            else
              this.provider = body;
          }
          else {
            this.error_message = 'username not found';
          }
        },
        () => {
          console.log('error');
        }
      )
    ;

  }

}
