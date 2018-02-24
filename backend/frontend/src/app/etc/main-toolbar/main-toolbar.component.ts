import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/authentication/user.service';
import {Router} from '@angular/router'

@Component({
  selector: 'app-main-toolbar',
  templateUrl: './main-toolbar.component.html',
  styleUrls: ['./main-toolbar.component.css']
})
export class MainToolbarComponent implements OnInit {
  username: string;
  isuser: boolean;
  constructor(private userService: UserService, private router: Router) { }

  ngOnInit() {
    this.username = this.userService.getUsername();
    this.isuser = this.userService.isUser();
  }
  logout() {
    this.userService.logout();
    this.router.navigate(['front-page']);
  }

}
