import {Component, OnInit} from '@angular/core';
import {Event} from '../../interfaces/event.interface';
import {EventService} from '../../services/event.service';
import {Observable} from 'rxjs/Observable';
import {Subject} from 'rxjs/Subject';
import {Filters} from '../../interfaces/filters.interface';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import {UserService} from '../../services/authentication/user.service';
import {FormsModule, FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-parent-events',
  templateUrl: './parent-events.component.html',
  styleUrls: ['./parent-events.component.css']
})
export class ParentEventsComponent implements OnInit {

  searchForm = new FormGroup({
    ageGroup: new FormControl(),
    category: new FormControl(),
    distance: new FormControl(),
    price: new FormControl()
  });

  eventsList: Event[] = [];
  listOrMap = 0;  // 0 is list, 1 is map

  newSearch: Filters = {
    username: '',
    price: 0,
    ageGroup: 0,
    category: 0,
    distance: 0,
    text: ''
  };

  constructor(private eventService: EventService, private userService: UserService) {
  }

  ngOnInit() {
    // this.getEvents();
    // this.setBounds();
    // this.isParent = this.userService.isParentUser();
  }

  onSubmit() {

  }

  searchEvents(): void {
    console.log(this.newSearch);
    this.newSearch.username = this.userService.getUsername();
    this.eventService.searchEvents(this.newSearch)
      .subscribe(data => {
        this.eventsList = data;
        console.log(this.eventsList);
      });
  }

}
