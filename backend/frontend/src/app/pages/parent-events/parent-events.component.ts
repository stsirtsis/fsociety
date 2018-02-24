import { Component, OnInit } from '@angular/core';
import { Event } from '../../interfaces/event.interface';
import { EventService } from '../../services/event.service';
import { LatLngBoundsLiteral } from './LatLngBoundsLiteral'
import { Observable } from 'rxjs/Observable';
import { Subject }    from 'rxjs/Subject';
import { Filters } from '../../interfaces/filters.interface';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import { UserService } from '../../services/authentication/user.service';

@Component({
  selector: 'app-parent-events',
  templateUrl: './parent-events.component.html',
  styleUrls: ['./parent-events.component.css']
})
export class ParentEventsComponent implements OnInit {

  eventsList: Event[];
  listOrMap: number = 0;  //0 is list, 1 is map
  boundsPar: LatLngBoundsLiteral={
  east: 0.0,
  west: 91.0,
  north: 0.0,
  south: 91.0
};


  newSearch: Filters = {
    username: '',
    price: 1,
    ageGroup: 1,
    category: 1,
    distance: 1,
    text: ''
  };

constructor(private eventService: EventService, private userService: UserService) { }

ngOnInit() {
  //this.getEvents();
  //this.setBounds();
  //this.isParent = this.userService.isParentUser();
}

searchEvents(): void{
  this.eventService.searchEvents(this.newSearch)
  .subscribe(data => this.eventsList = data);
}

getEvents(): void{
  this.eventsList = this.eventService.getAllEvents();
}

setBounds(): void{
  for (var ev in this.eventsList){
    if (this.eventsList[ev].latitude > this.boundsPar.north) this.boundsPar.north = this.eventsList[ev].latitude + 0.001;
    if (this.eventsList[ev].latitude < this.boundsPar.south) this.boundsPar.south = this.eventsList[ev].latitude - 0.001;
    if (this.eventsList[ev].longtitude > this.boundsPar.east) this.boundsPar.east = this.eventsList[ev].longtitude + 0.001;
    if (this.eventsList[ev].longtitude < this.boundsPar.west) this.boundsPar.west = this.eventsList[ev].longtitude - 0.001;
  }
}

}
