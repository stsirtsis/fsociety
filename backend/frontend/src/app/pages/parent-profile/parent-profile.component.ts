import { Component, OnInit } from '@angular/core';
import { UserService } from '../../services/authentication/user.service';


@Component({
  selector: 'app-parent-profile',
  templateUrl: './parent-profile.component.html',
  styleUrls: ['./parent-profile.component.css']
})
export class ParentProfileComponent implements OnInit {
  username: string;

  constructor(private userService: UserService) { }

  ngOnInit() {
    this.username = this.userService.getUsername();

  }

}
