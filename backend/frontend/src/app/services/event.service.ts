import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import { Event } from '../interfaces/event.interface';
import { Filters } from '../interfaces/filters.interface';
import { newEvent } from '../interfaces/newEvent.interface';
import { UserService } from './authentication/user.service';

@Injectable()
export class EventService {

  private POST_NEW_EVENT_URL;
  private POST_SEARCH_EVENT_URL;
  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private userService: UserService) { }

  createEvent(event: newEvent): Observable<newEvent> {
    this.POST_NEW_EVENT_URL = '/provider/addNewEvent/'+this.userService.getProviderCompanyName()+'?access_token='+this.userService.getAccessToken();
    return this.http.post<any>(this.POST_NEW_EVENT_URL, event, {headers: this.headers});
  }

  searchEvents(filters: Filters): Observable<Event[]>{
    this.POST_SEARCH_EVENT_URL = '/search/text/';
    return this.http.post<any>(this.POST_SEARCH_EVENT_URL, filters, {headers: this.headers});
    //return this.http.get<Event[]>(`search/text?text=${term}`, {responseType: 'json'});
  }
  
  getEventById(myId: number): Event{
    return null;
  }
}
