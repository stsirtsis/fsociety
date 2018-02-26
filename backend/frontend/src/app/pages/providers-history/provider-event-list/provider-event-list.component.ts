import { Component, OnInit, Input } from '@angular/core';
import { Event } from '../../../interfaces/event.interface';
import { SimpleChanges } from '@angular/core';

@Component({
  selector: 'app-provider-event-list',
  templateUrl: './provider-event-list.component.html',
  styleUrls: ['./provider-event-list.component.css']
})
export class ProviderEventListComponent implements OnInit {

  @Input() events: Event[];
  activePage: number = 1;
  eventsPerPage: number = 2;
  pagesList: number[] = [1];
  visibleEvents: Event[];
  constructor() { }


  ngOnInit() {

  }

  ngOnChanges(changes: SimpleChanges) {
    this.events = changes.events.currentValue;
    this.activePage=1;
    this.pagesList = [];

    for(var i = 0; i<this.events.length/this.eventsPerPage; i++){
      this.pagesList.push(i+1);
    }
    this.changePage(this.activePage);
  }

  changePage(current: number): void{
    if (current==0) current = 1;
    else if (current==this.pagesList.length+1) current = this.pagesList.length;
    this.activePage = current;
    this.visibleEvents = this.events.slice((current-1)*this.eventsPerPage, current*this.eventsPerPage);
  }

}
