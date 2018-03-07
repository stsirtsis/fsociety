import {Component, OnInit} from '@angular/core';
import {UserService} from '../../services/authentication/user.service';
import {Router} from '@angular/router';
import {TOKEN_NAME} from '../../constants/auth.constants';


@Component({
  selector: 'app-main-toolbar',
  templateUrl: './main-toolbar.component.html',
  styleUrls: ['./main-toolbar.component.css']
})
export class MainToolbarComponent implements OnInit {
  username: string;
  isuser = false;
  isparent = false;
  isprovider = false;
  isadmin = false;
  constructor(private userService: UserService, private router: Router) {
  }

  ngOnInit() {
    if (localStorage.getItem(TOKEN_NAME) == null) {
      console.log('not logged in');
    }
    else {
      this.userService.login(localStorage.getItem(TOKEN_NAME));
    }
    this.username = this.userService.getUsername();
    this.isuser = this.userService.isUser();
    this.isparent = this.userService.isParentUser();
    this.isprovider = this.userService.isProviderUser();
    this.isadmin = this.userService.isAdminUser();
  }

  logout() {
    this.userService.logout();
    this.isuser = false;
    this.isparent = false;
    this.isprovider = false;
    this.isadmin = false;
    this.router.navigate(['front-page']);
  }

}
