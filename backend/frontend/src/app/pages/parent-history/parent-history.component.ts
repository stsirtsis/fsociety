import { Component, OnInit } from '@angular/core';
import { Event } from '../../interfaces/event.interface';
import { EventService } from '../../services/event.service';
import { Observable } from 'rxjs/Observable';
import { Subject }    from 'rxjs/Subject';
import { Filters } from '../../interfaces/filters.interface';
import {ParentService} from '../../services/parent.service';

import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import { UserService } from '../../services/authentication/user.service';
import { FormsModule, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-parent-history',
  templateUrl: './parent-history.component.html',
  styleUrls: ['./parent-history.component.css']
})
export class ParentHistoryComponent implements OnInit {

  searchForm = new FormGroup({
    ageGroup: new FormControl(),
    category: new FormControl(),
    distance: new FormControl(),
    price: new FormControl()
  });



  eventsList: Event[] = [];
  listOrMap: number = 0;  //0 is list, 1 is map


  newSearch: Filters = {
    username: '',
    price: 0,
    ageGroup: 0,
    category: 0,
    distance: 0,
    text: '',
    area: '',
    streetName: '',
    streetNumber: 0
};

constructor(private eventService: EventService, private userService: UserService, private parentService: ParentService) { }

ngOnInit() {
  this.my_events();
  //this.getEvents();
  //this.setBounds();
  //this.isParent = this.userService.isParentUser();
}

searchEvents(): void{
  console.log(this.newSearch);
  this.newSearch.username = this.userService.getUsername();
  this.eventService.searchEvents(this.newSearch)
  .subscribe(data => {this.eventsList = data; console.log(this.eventsList);});
}

my_events(): void{
  this.parentService.getmyevents()
  .subscribe(
    data => {
      console.log('its ok');
      console.log(data);
      this.eventsList = data;

    },error => {console.log(error);});
}


}
