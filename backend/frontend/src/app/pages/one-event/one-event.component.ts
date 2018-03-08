import {Component, OnInit, Input} from '@angular/core';
import {Event} from '../../interfaces/event.interface';
import {EventService} from '../../services/event.service';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {UserService} from '../../services/authentication/user.service';
import {ParentService} from '../../services/parent.service';
import {CustomResponse} from '../../interfaces/customResponse.interface';
import {Providers} from '../../interfaces/providers.interface';



@Component({
  selector: 'app-one-event',
  templateUrl: './one-event.component.html',
  styleUrls: ['./one-event.component.css']
})
export class OneEventComponent implements OnInit {

  event= new Event() ;
  error2:string='';
  categoryString: string;
  ageGroupString: string;
  searchURLParameters: string[] = [];
  flag: boolean = false ;
  model: any = {};
  error: string = '';
  error1: string='';
  customResponse: CustomResponse;
  provider= new Providers();


  constructor(private parentService: ParentService,private eventService: EventService, private route: ActivatedRoute,private userService: UserService) {
  }

  ngOnInit() {
    this.error='';
    this.error1='';
    if(this.userService.isParentUser()){
       this.flag=true;
    }
    const id = +this.route.snapshot.paramMap.get('Id');
    this.searchURLParameters[0] = this.route.snapshot.paramMap.get('category');
    this.searchURLParameters[1] = this.route.snapshot.paramMap.get('text');
    this.searchURLParameters[2] = this.route.snapshot.paramMap.get('ageGroup');
    this.searchURLParameters[3] = this.route.snapshot.paramMap.get('distance');
    this.searchURLParameters[4] = this.route.snapshot.paramMap.get('price');
    this.searchURLParameters[5] = this.route.snapshot.paramMap.get('area');
    this.searchURLParameters[6] = this.route.snapshot.paramMap.get('streetName');
    this.searchURLParameters[7] = this.route.snapshot.paramMap.get('streetNumber');
    this.eventService.getEventById(id).
    subscribe(data => {
      this.event=data;
      console.log('TEST');
      console.log(this.event);
      if (this.event.category == 1) this.categoryString = "Αθλητισμός";
      else if (this.event.category == 2) this.categoryString = "Καλλιτεχνικά";
      else if (this.event.category == 3) this.categoryString = "Εκπαίδευση";
      if (this.event.ageGroup == 1) this.ageGroupString = "2-5";
      else if (this.event.ageGroup == 2) this.ageGroupString = "5-10";
      else if (this.event.ageGroup == 3) this.ageGroupString = "10-15";
      else this.ageGroupString = "15+";
      this.eventService.getpro(id).subscribe(data=>{
        this.provider = data;
        this.eventService.setclicks(id).subscribe();
      });



    });
  }

  buy_ticket1(num: number): void{
    this.error2='';
    this.error1='';
    this.error='';
      if (num<0)
         {
           this.error2='Λανθασμένο πλήθος εισητηρίων (απαραίτητα θετικό)';
         }

     else if((this.event.capacity - num )>=0){
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
  }
  else{
    this.error = 'Oι θέσεις δεν επαρκούν παρακαλώ επιλέξτε μέχρι και '+ this.event.capacity +' θέσεις';
    this.error1='';
    }
  }

}
