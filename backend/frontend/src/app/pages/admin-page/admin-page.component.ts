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

  userList: any[] = [];
  parentsList: Parent[];
  providersList: Providers[];
  ParorProv=0; //0 is Parent,1 is Providers

  constructor(private userProfileDataService: UserProfileDataService) {

  }

  ngOnInit() {
    this.getParents();
    this.getProviders();
    this.getAllUsers();
    $(document).ready(function(){
      $("#myInput").on("keyup", function() {
        var value = $(this).val().toLowerCase();
        $("#myTable tr").filter(function() {
          $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
        });
      });
    });
  }

  getAllUsers(): void {
    this.userProfileDataService.getUsers()
    .subscribe(data => {
      console.log("Hey Hey Hey");
      console.log(data);
      console.log(this.parentsList);
      console.log(this.providersList);
      console.log("Hey Hey Hey");
      for (let par of this.parentsList){
        for (let usr of data){
          if (usr.username === par.username){
            if (usr.roles.some(el => el.name === 'BLOCKED')) this.userList.push([par, 'BLOCKED', 'PARENT']);
            else this.userList.push([par, 'UNBLOCKED', 'PARENT']);
          }
        }
      }
      for (var prov of this.providersList){
        for (var usr of data){
          if (usr.username === prov.userName){
            if (usr.roles.some(el => el.name === 'BLOCKED')) this.userList.push([prov, 'BLOCKED', 'PROVIDER']);
            else this.userList.push([prov, 'UNBLOCKED', 'PROVIDER']);
          }
        }
      }
      console.log(this.userList);
    });
  }

  getParents(): void {
    this.userProfileDataService.getParents()
    .subscribe(data => {
      this.parentsList=data.body;
      console.log(data.body);
    },
    error=>{
      console.log(error);
    }
  );
}

getProviders(): void {
  this.userProfileDataService.getProviders()
  .subscribe(data => {
    this.providersList=data.body;
    console.log(data.body);
  },
  error=>{
    console.log(error);
  }
);
}

lockUser(x:string): void {
  for (var usr of this.userList){
    if (usr[2]==='PROVIDER'){
      if (usr[0].userName===x) usr[1]='BLOCKED'
    }
    if (usr[2]==='PARENT'){
      if (usr[0].username===x) usr[1]='BLOCKED'
    }
  }
  this.userProfileDataService.lockUser(x).
  subscribe(data => {console.log(data);}, error => {console.log(error);});
  console.log('blocked '+x);
}

unlockUser(x:string): void {
  for (var usr of this.userList){
    if (usr[2]==='PROVIDER'){
      if (usr[0].userName===x) usr[1]='UNBLOCKED'
    }
    if (usr[2]==='PARENT'){
      if (usr[0].username===x) usr[1]='UNBLOCKED'
    }
  }
  this.userProfileDataService.unlockUser(x).
  subscribe(data => {console.log(data);}, error => {console.log(error);});
  console.log ('unblocked '+x);
}
}
