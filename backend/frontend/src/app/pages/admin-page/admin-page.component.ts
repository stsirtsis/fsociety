import { Component, OnInit } from '@angular/core';
import {UserProfileDataService} from '../../services/user-profile-data.service';
import {Parent} from '../../interfaces/parent.interface';
import {Providers} from '../../interfaces/providers.interface';
declare var jquery:any;
declare var $ :any;


@Component({
  selector: 'app-admin-page',
  templateUrl: './admin-page.component.html',
  styleUrls: ['./admin-page.component.css']
})
export class AdminPageComponent implements OnInit {

  parentlist: Parent[] = [];
  constructor(private userProfileDataService: UserProfileDataService) {

   }
  providerslist:Providers[]=[];
  ParorProv=0; //0 is Parent,1 is Providers
  username:string;
  flag1=0;
  flag2=0;

  ngOnInit() {
  this.getParents();
  $(document).ready(function(){
    $("#myInput").on("keyup", function() {
      var value = $(this).val().toLowerCase();
      $("#myTable tr").filter(function() {
        $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
      });
    });
  });

  }
  getParents(): void {
    //console.log(this.newSearch);
    //this.newSearch.username = this.userService.getUsername();
    this.userProfileDataService.getParents()
      .subscribe(data => {
        this.parentlist=data.body;
        console.log(data.body);
      },
      error=>{
        console.log(error);
      }
    );
  }
  getProviders(): void {
    //console.log(this.newSearch);
    //this.newSearch.username = this.userService.getUsername();
    this.userProfileDataService.getProviders()
      .subscribe(data => {
        this.providerslist=data.body;
        console.log(data.body);
      },
      error=>{
        console.log(error);
      }
    );
  }
  lockUser(x:string): void {
    this.username=x;
    this.userProfileDataService.lockUser(this.username);
      console.log('blocked'+this.username);


  }
  unlockUser(x:string): void {
    this.username=x;
    this.userProfileDataService.unlockUser(this.username)
    console.log ('unblocked'+this.username);
  }





}
