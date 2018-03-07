import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';
import {UserService} from './authentication/user.service';


import {Providers} from '../interfaces/providers.interface';

@Injectable()
export class ProvidersService {

  private static POST_URL = '/register/provider';
  private POST_URL_EVENT ;
  private headers = new HttpHeaders({'Content-Type': 'application/json'});


  constructor(private http: HttpClient, private userService: UserService) {}

  createProvider(providers: Providers): Observable <any> {
    return this.http.post<any>(ProvidersService.POST_URL, providers, {observe : 'response'});
  }
  getmyevents(): Observable <any> {
    this.POST_URL_EVENT= '/provider/events/'+ this.userService.getUsername() + '?access_token=' + this.userService.getAccessToken();
    return this.http.post<any>(this.POST_URL_EVENT , {observe : 'response'});
 }

 getbyAge(age:number): Observable <any> {
   this.POST_URL_EVENT= '/provider/AgeGroup/'+ this.userService.getUsername() +'/'+age+ '?access_token=' + this.userService.getAccessToken();
   return this.http.post<any>(this.POST_URL_EVENT , {observe : 'response'});
}

 getbyCategory(cat:number):Observable <any> {
   this.POST_URL_EVENT= '/provider/CategoryGroup/'+ this.userService.getUsername() +'/'+cat+ '?access_token=' + this.userService.getAccessToken();
   return this.http.post<any>(this.POST_URL_EVENT , {observe : 'response'});
}
getmonth():Observable <any> {
  this.POST_URL_EVENT= '/provider/monthly/'+ this.userService.getUsername() +'?access_token=' + this.userService.getAccessToken();
  return this.http.post<any>(this.POST_URL_EVENT , {observe : 'response'});
}


}
