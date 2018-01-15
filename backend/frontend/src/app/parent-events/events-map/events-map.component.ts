import { Component, OnInit, Input } from '@angular/core';
import { Event } from '../../event.interface';
import { LatLngBoundsLiteral } from '../LatLngBoundsLiteral'

@Component({
  selector: 'app-events-map',
  templateUrl: './events-map.component.html',
  styleUrls: ['./events-map.component.css']
})
export class EventsMapComponent implements OnInit {

  @Input() events: Event[];
  @Input() bounds: LatLngBoundsLiteral;
  lat: number = 38.868973;
  lng: number = 23.751404;
  //zoom: number = 15;
  draggable: boolean = true;

  constructor() { }

  ngOnInit() { }

}
