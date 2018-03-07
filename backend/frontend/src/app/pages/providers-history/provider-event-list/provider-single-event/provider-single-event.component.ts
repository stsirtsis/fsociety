import { Component, OnInit, Input } from '@angular/core';
import { Event } from '../../../../interfaces/event.interface';
import { SimpleChanges } from '@angular/core';
import {EventService} from '../../../../services/event.service';


@Component({
  selector: 'app-provider-single-event',
  templateUrl: './provider-single-event.component.html',
  styleUrls: ['./provider-single-event.component.css']
})
export class ProviderSingleEventComponent implements OnInit {


  @Input() event:Event;
  shortDescription: string;
  isLong: boolean;
  tickets: number =0;
  m_tickets: number=0;
  clicks: number=0;


  constructor(private eventService: EventService) {
  }

  ngOnInit() {
    if (this.event.description.length < 100){
      this.shortDescription = this.event.description;
      this.isLong = false;
    }
    else{
      this.shortDescription = this.event.description.substring(0,100)+"...";
      this.isLong = true;
    }
    this.tickets = this.event.initial - this.event.capacity ;
    this.m_tickets = this.tickets * this.event.price;
    this.clicks = this.event.clicks;

  }

  ngOnChanges(changes: SimpleChanges) {
    this.event = changes.event.currentValue;
    if (this.event.description.length < 100){
      this.shortDescription = this.event.description;
      this.isLong = false;
    }
    else{
      this.shortDescription = this.event.description.substring(0,100)+"...";
      this.isLong = true;
    }
  }
}
