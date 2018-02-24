import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs/Observable';
import { of } from 'rxjs/observable/of';

import {Parent} from '../interfaces/parent.interface';

@Injectable()
export class ParentService {

  private POST_PARENT_URL = '/register/parent';
  private headers = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient) {}

  createParent(parent: Parent): Observable <Parent> {
    return this.http.post<any>(this.POST_PARENT_URL, parent, {headers: this.headers});
  }
  //getParents(): Observable <Parent[]> {
  //  return this.http.get<Parent[]>(ParentService.GET_PARENT_URL,{headers: this.headers});
  //}
}
