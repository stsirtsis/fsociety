import { Component, OnInit } from '@angular/core';
import { Event } from '../../interfaces/event.interface';
import { EventService } from '../../services/event.service';
import { LatLngBoundsLiteral } from './LatLngBoundsLiteral'

@Component({
  selector: 'app-parent-events',
  templateUrl: './parent-events.component.html',
  styleUrls: ['./parent-events.component.css']
})
export class ParentEventsComponent implements OnInit {

  eventsList: Event[];
  boundsPar: LatLngBoundsLiteral={
    east: 0.0,
    west: 91.0,
    north: 0.0,
    south: 91.0
  };

  constructor(private eventService: EventService) { }

  ngOnInit() {
    this.getEvents();
    this.setBounds();
  }

  getEvents(): void{
    this.eventsList = this.eventService.getEvents();
  }

  setBounds(): void{
    for (var ev in this.eventsList){
      //console.log(this.eventsList[ev].latitude+"")
      if (this.eventsList[ev].latitude > this.boundsPar.north) this.boundsPar.north = this.eventsList[ev].latitude + 0.001;
      if (this.eventsList[ev].latitude < this.boundsPar.south) this.boundsPar.south = this.eventsList[ev].latitude - 0.001;
      if (this.eventsList[ev].longtitude > this.boundsPar.east) this.boundsPar.east = this.eventsList[ev].longtitude + 0.001;
      if (this.eventsList[ev].longtitude < this.boundsPar.west) this.boundsPar.west = this.eventsList[ev].longtitude - 0.001;
    }
    //console.log(this.boundsPar);
  }

}
