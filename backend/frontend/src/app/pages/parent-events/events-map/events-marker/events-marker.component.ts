import { Component, OnInit, Input } from '@angular/core';
import { Event } from '../../../../interfaces/event.interface';

@Component({
  selector: 'app-events-marker',
  templateUrl: './events-marker.component.html',
  styleUrls: ['./events-marker.component.css']
})
export class EventsMarkerComponent implements OnInit {

  @Input() event: Event;
  shortDescription: string;

  constructor() {
  }

  ngOnInit() {
    if (this.event.description.length < 100) this.shortDescription = this.event.description;
    else this.shortDescription = this.event.description.substring(0,100)+"...";
  }

}
