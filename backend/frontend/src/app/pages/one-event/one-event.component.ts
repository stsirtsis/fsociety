import { Component, OnInit, Input } from '@angular/core';
import { Event } from '../../interfaces/event.interface';
import { EventService } from '../../services/event.service';
import { ActivatedRoute, ParamMap} from '@angular/router';
@Component({
  selector: 'app-one-event',
  templateUrl: './one-event.component.html',
  styleUrls: ['./one-event.component.css']
})
export class OneEventComponent implements OnInit {
  
  event: Event;
  constructor(private eventService: EventService, private route: ActivatedRoute) { }

  
  
  ngOnInit() {
    const id=+this.route.snapshot.paramMap.get('Id');

       this.event = this.eventService.getEventById(id);
  }
}
