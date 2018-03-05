import { Component, OnDestroy } from '@angular/core';
import { TOKEN_NAME } from './constants/auth.constants';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnDestroy {
  title = 'Fun Society';

  ngOnDestroy() {
    localStorage.removeItem('TOKEN_NAME');
  }
}
