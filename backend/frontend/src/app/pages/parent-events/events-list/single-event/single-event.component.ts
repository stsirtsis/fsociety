import {Component, OnInit, Input, OnChanges } from '@angular/core';
import {Event} from '../../../../interfaces/event.interface';
import {SimpleChanges} from '@angular/core';
import {UserService} from '../../../../services/authentication/user.service'
import {ParentService} from '../../../../services/parent.service'
import {CustomResponse} from '../../../../interfaces/customResponse.interface';
import {EventService} from '../../../../services/event.service'


declare var jquery:any;
declare var $ :any;

@Component({
  selector: 'app-single-event',
  templateUrl: './single-event.component.html',
  styleUrls: ['./single-event.component.css']
})
export class SingleEventComponent implements OnInit, OnChanges {

  @Input() event: Event;
  @Input() searchURLParameters: string[];
  shortDescription: string;
  isLong: boolean;
  error: string = '';
  flag: boolean = false ;
  customResponse: CustomResponse;
  model: any = {};


  constructor(private userService: UserService, private parentService: ParentService, private eventService: EventService) {
    }

  ngOnInit() {
    if(this.userService.isParentUser()){
       this.flag=true;
    }
  }

  ngOnChanges(changes: SimpleChanges) {

    this.event = changes.event.currentValue;
    if (this.event.description.length < 100) {
      this.shortDescription = this.event.description;
      this.isLong = false;
    }
    else {
      this.shortDescription = this.event.description.substring(0, 100) + '...';
      this.isLong = true;
    }
  }


}
