import { Component, OnInit, Input } from '@angular/core';
import { Event } from '../../../interfaces/event.interface';

@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css']
})
export class EventsListComponent implements OnInit {

  @Input() events: Event[];
  constructor() { }

  ngOnInit() {

  }

}
