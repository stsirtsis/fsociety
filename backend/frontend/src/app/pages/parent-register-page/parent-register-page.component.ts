import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';
import {Router} from '@angular/router';


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

  constructor(private parentService: ParentService,private router: Router,
    private userService: UserService) {}

    ngOnInit(): void {
      this.userService.logout();
    }
    onSubmit() {
      //if()
      //this.error = 'username already exists';
      this.parentService.createParent(this.parent).subscribe(
      value => {
        console.log('[POST] create Parent successfully', value);
      },
      () => {
        console.log('POST Parent - now completed.');
      });
      this.userService.isParent = true;
      this.userService.username = this.parent.username;
      this.router.navigate(['parent-events']);
    }
    
  }
