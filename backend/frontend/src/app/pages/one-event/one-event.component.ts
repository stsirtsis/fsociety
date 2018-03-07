import {Component, OnInit, Input} from '@angular/core';
import {Event} from '../../interfaces/event.interface';
import {EventService} from '../../services/event.service';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {UserService} from '../../services/authentication/user.service';
import {ParentService} from '../../services/parent.service';
import {CustomResponse} from '../../interfaces/customResponse.interface';



@Component({
  selector: 'app-one-event',
  templateUrl: './one-event.component.html',
  styleUrls: ['./one-event.component.css']
})
export class OneEventComponent implements OnInit {

  event= new Event() ;
  categoryString: string;
  ageGroupString: string;
  flag: boolean = false ;
  model: any = {};
  error: string = '';
  error1: string='';
  customResponse: CustomResponse;


  constructor(private parentService: ParentService,private eventService: EventService, private route: ActivatedRoute,private userService: UserService) {
  }

  ngOnInit() {
    this.error='';
    this.error1='';
    if(this.userService.isParentUser()){
       this.flag=true;
    }
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
      this.eventService.setclicks(id).subscribe();


    });
  }

  buy_ticket(num: number): void{
    if((this.event.capacity - num )>=0){
    this.parentService.buy_ticket(this.event.id,num).subscribe(data=>{
      this.customResponse = data.body;
      console.log(this.customResponse.message);
      this.event.capacity = this.event.capacity - num;
      if (this.customResponse.message === 'Sorry, event is full'){
        this.error = 'Sorry, event is full';
        this.error1='';
      }
      else{
        this.error1 = this.customResponse.message;
        this.error='';
        this.parentService.send_ticket_mail().subscribe(data=>{console.log(data);})
      }

    })
  }else if(this.event.capacity==0){
    this.error = 'Sorry, event is full';
    this.error1='';
  }else{
    this.error = 'Oι θέσεις δεν επαρκούν παρακαλώ επιλέξτε μέχρι και '+ this.event.capacity +' θέσεις';
    this.error1='';
    }
  }

}
