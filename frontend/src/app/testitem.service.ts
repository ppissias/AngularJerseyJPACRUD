import { Injectable } from '@angular/core';

import { Observable, of, throwError  } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { TestItem } from './testitem';

@Injectable({
  providedIn: 'root'
})

export class TestitemService {

  private serviceURL = 'http://localhost:8080/myapp/testitem';

  constructor(private http: HttpClient) { }

  getTestItems(): Observable<TestItem[]> {
    return  this.http.get<TestItem[]>(this.serviceURL)
  } 

  getTestItem(id: Number): Observable<TestItem> {
    console.log("service wlll get id!"+id);
    return  this.http.get<TestItem>(this.serviceURL+"/"+id);
  } 

  deleteTestItem(id: Number) : Observable<TestItem[]> {
    console.log("service wlll delete id!"+id);
    try {
      return this.http.delete<TestItem[]>(this.serviceURL+"/"+id);
    }
   catch(error) {
       // handle error
       console.error(error)
    }   
    
  }

  addTestItem(item: TestItem) {
    console.log("adding test item "+item);
    return  this.http.post<TestItem[]>(this.serviceURL, item);    
  }

  updateItem(item: TestItem)  {
    console.log("update test item "+item);
    return  this.http.put<void>(this.serviceURL, item);    
  }

 
}
