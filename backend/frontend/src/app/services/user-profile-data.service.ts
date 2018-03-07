import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UserService} from './authentication/user.service';
import {Parent} from '../interfaces/parent.interface';


@Injectable()
export class UserProfileDataService {

  private GET_URL = '/user_info';
  private GET_USERS_URL = '/admin/all_users'
  private GET_PARENT_URL='/admin/all_parents';
  private GET_PROVIDERS_URL='/admin/all_providers';
  private GET_LOCK_URL='/admin/lock_user';
  private GET_UNLOCK_URL='/admin/unlock_user';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  //parents: Parent[]=[];

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getUserData(username: string, access_token: string): Observable<any> {
    return this.http.get<any>(this.GET_URL + '?username=' + username
    + '&access_token=' + access_token,
    {observe: 'response'});
  }

  getUsers(): Observable <any> {
    return this.http.get<any>(this.GET_USERS_URL + '?access_token=' + this.userService.getAccessToken());
  }

  getParents(): Observable <any> {
    return this.http.get<any>(this.GET_PARENT_URL+ '?access_token=' + this.userService.getAccessToken(),{observe: 'response'});
  }

  getProviders(): Observable <any> {
    return this.http.get<any>(this.GET_PROVIDERS_URL+ '?access_token=' + this.userService.getAccessToken(),{observe: 'response'});
  }

  lockUser(username: string): Observable<any> {
    return this.http.get<any>(this.GET_LOCK_URL + '?username=' + username +
    '&access_token=' + this.userService.getAccessToken(),
    {observe: 'response'});
  }

  unlockUser(username: string): Observable<any> {
    return this.http.get<any>(this.GET_UNLOCK_URL + '?username=' + username +
    '&access_token=' + this.userService.getAccessToken(),
    {observe: 'response'});
  }

private GET_URL_RESET = '/register/reset_password';
send_email(username:string): Observable<any>{
    return this.http.get<any>(this.GET_URL_RESET + '?username=' + username,{observe: 'response'});
  }
}
