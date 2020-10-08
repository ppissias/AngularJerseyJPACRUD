import { Component, OnInit } from '@angular/core';

import {TestItem} from '../testitem';
import {TestitemService} from '../testitem.service';

import { Router } from '@angular/router';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-updateitem',
  templateUrl: './updateitem.component.html',
  styleUrls: ['./updateitem.component.css']
})
export class UpdateitemComponent implements OnInit {

  constructor(
    private router: Router,
    public testItemService: TestitemService,
    private route: ActivatedRoute
  ) { }

  id : Number;

  testItem: TestItem = {
    id: 0,
    descrpition: "",
  }

  ngOnInit() {
    console.log("init");
    this.route.params.subscribe(params => {
      console.log("will get with id "+params['itemid']);
      this.id = +params['itemid']; // (+) converts string 'id' to a number
      this.testItemService.getTestItem(this.id).subscribe(
        testItem => {this.testItem = testItem; console.log("updated test item");}
      );
    });
  }

  onSubmit() {
    console.log("test Item "+this.testItem.descrpition);

    this.testItemService.updateItem(this.testItem).subscribe();
    this.router.navigateByUrl("/");
  }
}
