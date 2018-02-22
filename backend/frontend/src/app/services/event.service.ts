import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import {Event} from '../interfaces/event.interface';
import { EventsList } from '../etc/mock-events';

@Injectable()
export class EventService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  constructor(private http: HttpClient) { }

  getAllEvents(): Event[]{
    return EventsList;
  }

  getEventsByText(term: string): Observable<Event[]>{
    return this.http.get<Event[]>(`search/text?text=${term}`, {responseType: 'json'});
  }

  getEventById(myId: number): Event{
    for (var ev of EventsList){
      if (ev.Id == myId) return ev;
    }
  }
}
