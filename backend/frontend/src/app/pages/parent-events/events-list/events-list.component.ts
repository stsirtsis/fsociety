import {Component, OnInit, Input, OnChanges } from '@angular/core';
import {Event} from '../../../interfaces/event.interface';
import {SimpleChanges} from '@angular/core';
declare var jquery:any;
declare var $ :any;

@Component({
  selector: 'app-events-list',
  templateUrl: './events-list.component.html',
  styleUrls: ['./events-list.component.css']
})
export class EventsListComponent implements OnInit, OnChanges {

  @Input() events: Event[];
  @Input() searchURLParameters: string[];
  activePage = 1;
  eventsPerPage = 3;
  pagesList: number[] = [1];
  visibleEvents: Event[];

  constructor() {
  }


  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges) {
    this.events = changes.events.currentValue;
    this.activePage = 1;
    this.pagesList = [];

    for (let i = 0; i < this.events.length / this.eventsPerPage; i++) {
      this.pagesList.push(i + 1);
    }
    this.changePage(this.activePage);
  }

  changePage(current: number): void {
    if (current === 0)
      current = 1;
    else if (current === this.pagesList.length + 1)
      current = this.pagesList.length;

    this.activePage = current;
    this.visibleEvents = this.events.slice((current - 1) * this.eventsPerPage, current * this.eventsPerPage);
  }

}
