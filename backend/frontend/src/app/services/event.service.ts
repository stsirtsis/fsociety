import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import {Event} from '../interfaces/event.interface';
import { EventsList } from '../etc/mock-events';

@Injectable()
export class EventService {

  constructor(private http: HttpClient) { }

  getAllEvents(): Event[]{
    return EventsList;
  }
  
  getEventById(myId: number): Event{
    for (var ev of EventsList){
      if (ev.Id == myId) return ev;
    }
    //console.log(EventsList.filter(x => (x.Id===myid)));
    //return EventsList.filter(x => (x.Id===myid));
  }
}

