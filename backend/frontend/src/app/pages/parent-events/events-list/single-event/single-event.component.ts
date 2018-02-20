import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-single-event',
  templateUrl: './single-event.component.html',
  styleUrls: ['./single-event.component.css']
})
export class SingleEventComponent implements OnInit {

  @Input() event: Event;
  shortDescription: string;
  isLong: boolean;

  constructor() {
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

  //getShortDescription(): string{

  //}
}
