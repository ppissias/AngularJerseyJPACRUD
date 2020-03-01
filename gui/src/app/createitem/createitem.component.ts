import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import {TestItem} from '../testitem';
import {TestitemService} from '../testitem.service';

import { Router } from '@angular/router';

@Component({
  selector: 'app-createitem',
  templateUrl: './createitem.component.html',
  styleUrls: ['./createitem.component.css']
})
export class CreateitemComponent implements OnInit {

  constructor(
    private router: Router,
    public testItemService: TestitemService
  ) { }

  testItem: TestItem = {
    id: 0,
    transactionDate: "",
    descrpition: "",
    detailedDescription: "",
    amount: 0, 
    category: ""
  }

  ngOnInit() {
  }

  onSubmit() {
    console.log("test Item "+this.testItem.transactionDate);
   
  }
}
