import {Component, OnInit, Input, OnChanges} from '@angular/core';
import {Event} from '../../../../interfaces/event.interface';
import {SimpleChanges} from '@angular/core';

@Component({
  selector: 'app-parent-single-event',
  templateUrl: './parent-single-event.component.html',
  styleUrls: ['./parent-single-event.component.css']
})
export class ParentSingleEventComponent implements OnInit, OnChanges {


  @Input() event: Event;
  shortDescription: string;
  isLong: boolean;

  constructor() {
  }

  ngOnInit() {
    if (this.event.description.length < 100) {
      this.shortDescription = this.event.description;
      this.isLong = false;
    }
    else {
      this.shortDescription = this.event.description.substring(0, 100) + '...';
      this.isLong = true;
    }
  }

  ngOnChanges(changes: SimpleChanges) {
    this.event = changes.event.currentValue;
    if (this.event.description.length < 100) {
      this.shortDescription = this.event.description;
      this.isLong = false;
    }
    else {
      this.shortDescription = this.event.description.substring(0, 100) + '...';
      this.isLong = true;
    }
  }
}
