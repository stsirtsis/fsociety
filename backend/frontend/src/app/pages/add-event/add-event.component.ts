import { Component, OnInit } from '@angular/core';
import { FormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import {newEvent} from '../../interfaces/newEvent.interface';
import {EventService} from '../../services/event.service'

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  eventForm = new FormGroup({
    AgeGroup: new FormControl(),
    category: new FormControl()
  });

  event: newEvent = {
    eventName: '',
    description: '',
    area: '',
    streetName: '',
    streetNumber: 0,
    ageGroup: 1,
    capacity: 0,
    price: 0,
    category: 1,
    date: '',
    state: 'OPEN',
    parents: []
  };

  constructor(private eventService: EventService) { }

  ngOnInit() {

  }

  onSubmit() {
    console.log(this.event);
    this.eventService.createEvent(this.event).subscribe(
      value => {
        console.log('[POST] create Event successfully', value);
      },
      () => {
        console.log('POST Event - now completed.');
      });
    }
}
