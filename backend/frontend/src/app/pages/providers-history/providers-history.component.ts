import { Component, OnInit } from '@angular/core';
import { Event } from '../../interfaces/event.interface';
import { EventService } from '../../services/event.service';
import { Observable } from 'rxjs/Observable';
import { Subject }    from 'rxjs/Subject';
import { Filters } from '../../interfaces/filters.interface';
import {
  debounceTime, distinctUntilChanged, switchMap
} from 'rxjs/operators';
import { UserService } from '../../services/authentication/user.service';
import {ProvidersService} from '../../services/providers.service';
import { FormsModule, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-providers-history',
  templateUrl: './providers-history.component.html',
  styleUrls: ['./providers-history.component.css']
})
export class ProvidersHistoryComponent implements OnInit {

  searchForm = new FormGroup({
    ageGroup: new FormControl(),
    category: new FormControl(),
    distance: new FormControl(),
    price: new FormControl()
  });
  cat1_stats: number = 0;
  cat2_stats: number = 0;
  cat3_stats: number = 0;
  age1_stats: number = 0;
  age2_stats: number = 0;
  age3_stats: number = 0;
  age4_stats: number = 0;

  eventsList: Event[] = [];
  listOrMap: number = 0;  //0 is list, 1 is map
  tempList: Event[] = [];


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

  i: any;
  temp: number = 0;
  monthly_t: number=0;
  monthly_m: number =0;
  sum_t: number= 0 ;

constructor(private eventService: EventService, private userService: UserService, private providersService: ProvidersService) { }

ngOnInit() {
  this.my_events();
}

searchEvents(): void{
  console.log(this.newSearch);
  this.newSearch.username = this.userService.getUsername();
  this.eventService.searchEvents(this.newSearch)
  .subscribe(data => {this.eventsList = data; console.log(this.eventsList);});
}

stats_tickets(stat_list: any): number{
  this.temp = 0;
  for(this.i in stat_list){
  this.temp +=stat_list[this.i].initial - stat_list[this.i].capacity;
}
return this.temp;
}
stats_money(stat_list: any): number{
  this.temp = 0;
  for(this.i in stat_list){
  this.temp +=(stat_list[this.i].initial - stat_list[this.i].capacity)*stat_list[this.i].price;
}
return this.temp;
}

my_events(): void{
  this.providersService.getmyevents()
  .subscribe(
    data => {
      console.log(data);
      this.eventsList = data;
      this.sum_t = this.stats_tickets(this.eventsList);

     this.providersService.getmonth().subscribe(data => {
          this.tempList = data;
          this.monthly_t = this.stats_tickets(this.tempList);
          this.monthly_m = this.stats_money(this.tempList);
          this.my_events_byCategory();

        },
          error => {console.log(error);});

    },error => {console.log(error);});
}

my_events_byCategory(): void{
  this.providersService.getbyCategory(1).subscribe(data => {
      console.log('byGroup');
      this.tempList = data;
      if(this.sum_t != 0){
      this.cat1_stats = this.stats_tickets(this.tempList)*100/this.sum_t;
    }

    this.providersService.getbyCategory(2).subscribe(data => {
        this.tempList = data;
        if(this.sum_t != 0){
        this.cat2_stats = this.stats_tickets(this.tempList)*100/this.sum_t;
      }

        this.providersService.getbyCategory(3).subscribe(data => {
            this.tempList = data;
            if(this.sum_t != 0){
            this.cat3_stats = this.stats_tickets(this.tempList)*100/this.sum_t;
          }

          this.my_events_byAge();
          },
            error => {console.log(error);});
      },
        error => {console.log(error);});
  },
      error => {console.log(error);});
}


my_events_byAge(): void{
  this.providersService.getbyAge(1).subscribe(data => {
      console.log('byAge');
      this.tempList = data;
      if(this.sum_t != 0){
      this.age1_stats = this.stats_tickets(this.tempList)*100/this.sum_t;
    }

    this.providersService.getbyAge(2).subscribe(data => {
        this.tempList = data;
        if(this.sum_t != 0){
        this.age2_stats = this.stats_tickets(this.tempList)*100/this.sum_t;
      }

        this.providersService.getbyAge(3).subscribe(data => {
            this.tempList = data;
            if(this.sum_t != 0){
            this.age3_stats = this.stats_tickets(this.tempList)*100/this.sum_t;
          }

          this.providersService.getbyAge(4).subscribe(data => {
              this.tempList = data;
              if(this.sum_t != 0){
              this.age4_stats = this.stats_tickets(this.tempList)*100/this.sum_t;
            }


            },
              error => {console.log(error);});
          },
            error => {console.log(error);});
      },
        error => {console.log(error);});
  },
      error => {console.log(error);});
}



}
