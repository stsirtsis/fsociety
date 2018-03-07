import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/authentication/user.service';
import {UserProfileDataService} from '../../services/user-profile-data.service';
import {Parent} from '../../interfaces/parent.interface';


@Component({
  selector: 'app-parent-profile',
  templateUrl: './parent-profile.component.html',
  styleUrls: ['./parent-profile.component.css']
})
export class ParentProfileComponent implements OnInit {
  username: string;
  access_token: string;
  parent = new Parent();
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
          if (this.userService.isParent) {
            if (typeof body === 'string')
              this.error_message = 'username not found';
            else
              this.parent = body;
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
export class NgbdPopoverBasic {
}
