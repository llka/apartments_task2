import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UsersComponent} from "./users/users.component";
import {ApartmentsComponent} from "./apartments/apartments.component";

const routes: Routes = [
  {path: 'user', component: UsersComponent},
  {path: 'apartment', component: ApartmentsComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})

export class AppRoutingModule {}
