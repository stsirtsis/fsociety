import { Component, OnInit, Input } from '@angular/core';
import { Event } from '../../../interfaces/event.interface';
import { LatLngBoundsLiteral } from './LatLngBoundsLiteral'
import { SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-events-map',
  templateUrl: './events-map.component.html',
  styleUrls: ['./events-map.component.css']
})
export class EventsMapComponent implements OnInit {

  @Input() events: Event[];
  @Input() searchURLParameters: string[];

  centerLat: number;
  centerLng: number;
  draggable: boolean = true;
  mapBounds: LatLngBoundsLiteral;
  numOfEvent: number;
  constructor() { }

  ngOnInit() {
    //this.setBoundsAndCenter();
  }

  ngOnChanges(changes: SimpleChanges) {
    this.numOfEvent = 1;
    this.events = changes.events.currentValue;
    this.setBoundsAndCenter();
    console.log(this.mapBounds);
    console.log(this.centerLat);
    console.log(this.centerLng);
  }

  setBoundsAndCenter(): void{
    this.mapBounds={
      east: 0.0,
      west: 91.0,
      north: 0.0,
      south: 91.0
    };
    this.centerLat=0;
    this.centerLng=0;
    for (var ev in this.events){
      if (parseFloat(this.events[ev].latitude) > this.mapBounds.north) this.mapBounds.north = parseFloat(this.events[ev].latitude) + 0.001;
      if (parseFloat(this.events[ev].latitude) < this.mapBounds.south) this.mapBounds.south = parseFloat(this.events[ev].latitude) - 0.001;
      if (parseFloat(this.events[ev].longitude) > this.mapBounds.east) this.mapBounds.east = parseFloat(this.events[ev].longitude) + 0.001;
      if (parseFloat(this.events[ev].longitude) < this.mapBounds.west) this.mapBounds.west = parseFloat(this.events[ev].longitude) - 0.001;
      this.centerLat += parseFloat(this.events[ev].latitude);
      this.centerLng += parseFloat(this.events[ev].longitude);
    }
    this.centerLat /= this.events.length;
    this.centerLng /= this.events.length;
  }
}
