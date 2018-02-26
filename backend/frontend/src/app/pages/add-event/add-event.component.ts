import { Component, OnInit } from '@angular/core';
import { FormsModule, FormControl, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpResponse, HttpEventType } from '@angular/common/http';
import {newEvent} from '../../interfaces/newEvent.interface';
import {EventService} from '../../services/event.service'
import {Router} from '@angular/router'

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  selectedFiles: FileList
  currentFileUpload: File
  progress: { percentage: number } = { percentage: 0 }

  eventForm = new FormGroup({
    AgeGroup: new FormControl(),
    category: new FormControl()
  });

  event: newEvent = {
    eventname: '',
    description: '',
    area: '',
    streetName: '',
    streetNumber: 0,
    ageGroup: 1,
    capacity: 0,
    price: 0,
    category: 1,
    date: '',
    state: 'OPEN',
    parents: [],
    clicks: 0,
    initial: 0
  };

  constructor(private eventService: EventService , private router : Router) { }

  ngOnInit() {

  }

  selectFile(event) {
    const file = event.target.files.item(0)

    if (file.type.match('image.*')) {
      this.selectedFiles = event.target.files;
    } else {
      alert('invalid format!');
    }
  }

  onSubmit() {

    this.progress.percentage = 0;

    this.currentFileUpload = this.selectedFiles.item(0)
    this.eventService.createEvent(this.event, this.currentFileUpload).subscribe(event => {
      if (event.type === HttpEventType.UploadProgress) {
        this.progress.percentage = Math.round(100 * event.loaded / event.total);
      } else if (event instanceof HttpResponse) {
        console.log('File is completely uploaded!');
      }
    })

    this.selectedFiles = undefined

    /*this.eventService.createEvent(this.event).subscribe(
      value => {
        console.log('[POST] create Event successfully', value);
      },
      () => {
        console.log('POST Event - now completed.');
        this.router.navigate(['front-page']);
      });*/
    }
}
