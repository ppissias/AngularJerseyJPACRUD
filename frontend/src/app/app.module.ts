import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DisplayitemlistComponent } from './displayitemlist/displayitemlist.component';
import { CreateitemComponent } from './createitem/createitem.component';
import { UpdateitemComponent } from './updateitem/updateitem.component';

import { HttpClientModule }    from '@angular/common/http';

import { RouterModule, Routes } from '@angular/router';
import { FormsModule } from '@angular/forms';

import { MatSliderModule } from '@angular/material/slider';
import {MatGridListModule} from '@angular/material/grid-list'; 
import {MatTabsModule} from '@angular/material/tabs'; 
import {MatButtonModule} from '@angular/material/button';
import {MatTableModule} from '@angular/material/table'; 
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatIconModule} from '@angular/material/icon';

@NgModule({
  declarations: [
    AppComponent,
    DisplayitemlistComponent,
    CreateitemComponent,
    UpdateitemComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatSliderModule,
    MatGridListModule,
    MatTabsModule,
    MatButtonModule,
    MatTableModule,
    MatInputModule,
    HttpClientModule,
    MatFormFieldModule,
    MatIconModule,
    FormsModule,
    RouterModule.forRoot([      
      { path: '', component: DisplayitemlistComponent }, //root path
      { path: 'updateitem/:itemid', component: UpdateitemComponent },
      { path: 'createitem', component: CreateitemComponent },
    ])        
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
