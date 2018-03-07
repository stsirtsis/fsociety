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
  shortDescription: string;
  isLong: boolean;
  error: string = '';
  flag: boolean = false ;
  customResponse: CustomResponse;
  model: any = {};
  flag_b: boolean = false;

  event_id : number;
  event_cap : number;

  constructor(private userService: UserService, private parentService: ParentService, private eventService: EventService) {
    }

  ngOnInit() {
    if(this.userService.isParentUser()){
       this.flag=true;
    }
    this.event_id = this.event.id;
    this.event_cap = this.event.capacity;

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

  buy_ticket(num: number,event_id: number, event_cap: number): void{
    if((event_cap - num )>=0){
    this.parentService.buy_ticket(event_id,num).subscribe(data=>{
      this.customResponse = data.body;
      console.log(this.customResponse.message);
      console.log(event_id);
      event_cap = this.model.event.capacity - num;
      if (this.customResponse.message === 'Sorry, event is full'){
        this.error = 'Sorry, event is full';
      }
      else{
        this.error = this.customResponse.message;
      }

    })
  }else if(event_cap==0){
    this.error = 'Sorry, event is full';
  }else{
    this.error = 'Oι θέσεις δεν επαρκούν παρακαλώ επιλέξτε μέχρι και '+ event_cap +' θέσεις';
  }
}
}
