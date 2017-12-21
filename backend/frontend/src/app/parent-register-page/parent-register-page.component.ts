import { Component, OnInit } from '@angular/core';
import {FormsModule} from '@angular/forms';

import {Parent} from '../parent.interface';
import {ParentService} from '../parent.service';

@Component({
  selector: 'app-parent-register-page',
  templateUrl: './parent-register-page.component.html',
  styleUrls: ['./parent-register-page.component.css']
})
export class ParentRegisterPageComponent implements OnInit {

  parent: Parent  = { firstName: '', lastName: '', username: '', password: '', email: '', phoneNumber: '', debitCard: '', Fpoints: 0};

  constructor(private parentService: ParentService) {}

  ngOnInit() {
  }
    onSubmit() {
    this.parentService.createParent(this.parent).subscribe(
      value => {
        console.log('[POST] create Parent successfully', value);
      },
      () => {
        console.log('POST Parent - now completed.');
      });
  }

}
