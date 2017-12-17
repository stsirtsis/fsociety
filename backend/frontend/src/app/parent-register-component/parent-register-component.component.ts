import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-parent-register-component',
  templateUrl: './parent-register-component.component.html',
  styleUrls: ['./parent-register-component.component.css']
})
 
export class ParentRegisterComponent implements OnInit {
  title = 'Parent Registration Form';
  constructor() { }

  ngOnInit() {
  }

}
