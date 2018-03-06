import {Component, OnInit, Input} from '@angular/core';
import {Event} from '../../interfaces/event.interface';
import {EventService} from '../../services/event.service';
import {ActivatedRoute, ParamMap} from '@angular/router';

@Component({
  selector: 'app-one-event',
  templateUrl: './one-event.component.html',
  styleUrls: ['./one-event.component.css']
})
export class OneEventComponent implements OnInit {

  event: Event;
  categoryString: string;
  ageGroupString: string;

  constructor(private eventService: EventService, private route: ActivatedRoute) {
  }

  ngOnInit() {
    const id = +this.route.snapshot.paramMap.get('Id');
    this.eventService.getEventById(id).
    subscribe(data => {
      this.event=data;
      console.log(this.event);
      if (this.event.category == 1) this.categoryString = "Αθλητισμός";
      else if (this.event.category == 2) this.categoryString = "Καλλιτεχνικά";
      else if (this.event.category == 3) this.categoryString = "Εκπαίδευση";
      if (this.event.AgeGroup == 1) this.ageGroupString = "2-5";
      else if (this.event.AgeGroup == 2) this.ageGroupString = "5-10";
      else if (this.event.AgeGroup == 3) this.ageGroupString = "10-15";
      else this.ageGroupString = "15+";
    });
  }
}
