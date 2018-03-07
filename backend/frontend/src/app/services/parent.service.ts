import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import {UserService} from './authentication/user.service';

import {Parent} from '../interfaces/parent.interface';
import { CustomResponse } from '../interfaces/customResponse.interface';

@Injectable()
export class ParentService {

  private POST_PARENT_URL = '/register/parent';
  private POST_URL_EVENT ;
  private GET_URL_EVENT;
  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private userService: UserService) {}

  createParent(parent: Parent): Observable <any> {
    return this.http.post<any>(this.POST_PARENT_URL, parent, {observe : 'response'});
  }
  getmyevents(): Observable <any> {
    this.POST_URL_EVENT= '/parents/eventParent/'+ this.userService.getUsername() + '?access_token=' + this.userService.getAccessToken();
    return this.http.post<any>(this.POST_URL_EVENT , {observe : 'response'});
  }
  buy_ticket(event_id: number , num: number): Observable <any> {
    this.GET_URL_EVENT= '/buyticket/new'+ '?parent_username='+this.userService.getUsername()+'&event_id='+event_id+ '&quantity='+num+ '&access_token=' + this.userService.getAccessToken();
    return this.http.get<any>(this.GET_URL_EVENT , {observe : 'response'});
  }
  send_ticket_mail(): Observable <any> {
    this.GET_URL_EVENT= '/buyticket/sendEmail'+ '?username='+this.userService.getUsername()+'&access_token=' + this.userService.getAccessToken();
    return this.http.get<any>(this.GET_URL_EVENT , {observe : 'response'});
  }
}
