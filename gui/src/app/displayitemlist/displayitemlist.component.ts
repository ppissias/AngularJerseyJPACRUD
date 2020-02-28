import { Component, OnInit } from '@angular/core';

import {TestItem} from '../testitem';
import {TestitemService} from '../testitem.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-displayitemlist',
  templateUrl: './displayitemlist.component.html',
  styleUrls: ['./displayitemlist.component.css']
})


export class DisplayitemlistComponent implements OnInit {

  constructor(private testItemService: TestitemService, private router: Router) { }

  testItems: TestItem[]; 
  testItem: TestItem;

  displayedColumns: string[] = ['id', 'amount', 'descrpition', 'detailedDescription','transactionDate','category','action'];
 
  ngOnInit() {
    console.log("initiated");
  }

  getTestItems(): void {
    this.testItemService.getTestItems().subscribe(
        testItems => this.testItems = testItems
    );
  }

  getItems() {
    console.log("will fetch the test items!");
    this.getTestItems();
  }

  getItem(id: Number) {
    console.log("will fetch the test item "+id);
    this.testItemService.getTestItem(id).subscribe(
      testItem => this.testItem = testItem
    );
  }  

  deleteItem(id: Number) {
    //this.getTestItems();
    console.log("wlll delete id!"+id);
    this.testItemService.deleteTestItem(id).subscribe(
      testItems => this.testItems = testItems
    ); 
  }  

  updateItem(id: Number)  {
    //this.getTestItems();
    console.log("wlll update id!"+id);
  }

  addItem() {
    console.log("adding new item");
    this.router.navigateByUrl("/createitem");
  }

  navigateUpdate(id: Number) {
    this.router.navigateByUrl("/updateitem/"+id);
  }

}
