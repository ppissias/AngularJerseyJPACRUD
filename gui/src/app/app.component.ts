import { Component } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'guis';

  constructor(private router: Router) { }

  addItem() {
    console.log("adding new item");
    this.router.navigateByUrl("/createitem"); /* router is defined in main app module */
  }  
}
