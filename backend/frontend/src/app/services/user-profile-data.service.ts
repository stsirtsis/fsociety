import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import {UserService} from './authentication/user.service';

@Injectable()
export class UserProfileDataService {

  private GET_URL = '/user_info';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient, private userService: UserService) {
  }

  getUserData(username: string, access_token: string): Observable<any> {
    return this.http.get<any>(this.GET_URL + '?username=' + username
      + '&access_token=' + access_token,
      {observe: 'response'});
  }

}
