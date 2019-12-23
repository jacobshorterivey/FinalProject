import { FosterListComponent } from './components/foster-list/foster-list.component';
import { ShelternavComponent } from './components/shelternav/shelternav.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShelterListComponent } from './components/shelter-list/shelter-list.component';
import { ShelterProfileComponent } from './components/shelter-profile/shelter-profile.component';
import { PetProfileComponent } from './components/pet-profile/pet-profile.component';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'fosters', component: FosterListComponent},
  {path: 'shelters', component: ShelterListComponent},
  {path: 'shelter/:id', component: ShelterProfileComponent},
  {path: 'shelter', component: ShelterProfileComponent},
  {path: 'shelternav', component: ShelternavComponent},
  {path: 'pet', component: PetProfileComponent},
  {path: '**', component: NotFoundComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
