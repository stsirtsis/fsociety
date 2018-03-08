import {Component, OnInit} from '@angular/core';
import {Event} from '../../interfaces/event.interface';
import {EventService} from '../../services/event.service';
import {Observable} from 'rxjs/Observable';
import {Subject} from 'rxjs/Subject';
import {Filters} from '../../interfaces/filters.interface';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import {UserService} from '../../services/authentication/user.service';
import {FormsModule, FormControl, FormGroup, Validators} from '@angular/forms';
declare var jquery:any;
declare var $ :any;

@Component({
  selector: 'app-parent-events',
  templateUrl: './parent-events.component.html',
  styleUrls: ['./parent-events.component.css']
})
export class ParentEventsComponent implements OnInit {
   per='';
   error0='';

  searchForm = new FormGroup({
    ageGroup: new FormControl(),
    category: new FormControl(),
    distance: new FormControl(),
    price: new FormControl()
  });

  searchURLParameters: string[] = [
    '0',
    '',
    '0',
    '0',
    '0',
    '',
    '',
    '0'
  ];

  isAnonymous: boolean;
  eventsList: Event[] = [];
  listOrMap = 0;  // 0 is list, 1 is map

  newSearch: Filters = {
    username: '',
    price: 0,
    ageGroup: 0,
    category: 0,
    distance: 0,
    text: '',
    area: '',
    streetName: '',
    streetNumber: 0
  };

  constructor(private eventService: EventService, private userService: UserService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.error0='';
    this.newSearch.category = +this.route.snapshot.paramMap.get('category');
    this.newSearch.text = this.route.snapshot.paramMap.get('text');
    this.newSearch.ageGroup = +this.route.snapshot.paramMap.get('ageGroup');
    this.newSearch.distance = +this.route.snapshot.paramMap.get('distance');
    this.newSearch.price = +this.route.snapshot.paramMap.get('price');
    this.newSearch.area = this.route.snapshot.paramMap.get('area');
    this.newSearch.streetName = this.route.snapshot.paramMap.get('streetName');
    this.newSearch.streetNumber = +this.route.snapshot.paramMap.get('streetNumber');
    this.per=this.newSearch.area;
    this.isAnonymous = !this.userService.isUser();
    this.searchEvents();


        function toggleChevron(e) {
		$(e.target)
				.prev('.panel-heading')
				.find("i.indicator")
				.toggleClass('fa-caret-down fa-caret-right');
	}
	$('#accordion').on('hidden.bs.collapse', toggleChevron);
	$('#accordion').on('shown.bs.collapse', toggleChevron);
  }

  onSubmit() {

  }

  searchEvents(): void {
    this.error0='';
    this.per=this.newSearch.area;
    this.searchURLParameters[0] = this.newSearch.category+"";
    this.searchURLParameters[1] = this.newSearch.text;
    this.searchURLParameters[2] = this.newSearch.ageGroup+"";
    this.searchURLParameters[3] = this.newSearch.distance+"";
    this.searchURLParameters[4] = this.newSearch.price+"";
    this.searchURLParameters[5] = this.newSearch.area;
    this.searchURLParameters[6] = this.newSearch.streetName;
    this.searchURLParameters[7] = this.newSearch.streetNumber+"";
    console.log(this.newSearch);
    if (!this.userService.isUser()) this.newSearch.username = '';
    else this.newSearch.username = this.userService.getUsername();
    this.eventService.searchEvents(this.newSearch)
      .subscribe(data => {
        this.eventsList = data;
        console.log(this.eventsList);
      });
      if(this.per==='')
         this.error0='Περιοχή,Οδός,Αριθμός απαιτούνται';
  }

}
