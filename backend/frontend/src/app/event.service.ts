import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import {Event} from './event.interface';
import { EventsList } from './mock-events';

@Injectable()
export class EventService {

  constructor(private http: HttpClient) { }

  getEvents(): Event[]{
    return EventsList;
  }
}
