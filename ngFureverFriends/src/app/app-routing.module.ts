import { HomeComponent } from './components/home/home.component';
import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShelterListComponent } from './components/shelter-list/shelter-list.component';
import { ShelterProfileComponent } from './components/shelter-profile/shelter-profile.component';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'shelters', component: ShelterListComponent},
  {path: 'shelter', component: ShelterProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
