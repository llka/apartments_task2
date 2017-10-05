import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {DataService} from './data.service';
import { UsersComponent } from './users/users.component';
import { UserManipulatorComponent } from './user-manipulator/user-manipulator.component';
import { ApartmentsComponent } from './apartments/apartments.component';
import {AppRoutingModule} from './app-routing.module';
import {HttpModule} from "@angular/http";

@NgModule({
  declarations: [
    AppComponent,
    UsersComponent,
    UserManipulatorComponent,
    ApartmentsComponent
  ],
  imports: [
    BrowserModule,
    HttpModule,
    AppRoutingModule
  ],
  providers: [DataService],
  bootstrap: [AppComponent]
})
export class AppModule { }
