import { AdminPageComponent } from './components/admin-page/admin-page.component';
import { PetSearchComponent } from './components/pet-search/pet-search.component';
import { LoginComponent } from './components/login/login.component';
import { FosterListComponent } from './components/foster-list/foster-list.component';
import { ShelternavComponent } from './components/shelternav/shelternav.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { NavSearchComponent } from './components/nav-search/nav-search.component';
import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShelterListComponent } from './components/shelter-list/shelter-list.component';
import { ShelterProfileComponent } from './components/shelter-profile/shelter-profile.component';
import { PetProfileComponent } from './components/pet-profile/pet-profile.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { UserListComponent } from './components/user-list/user-list.component';
import { RegisterComponent } from './components/register/register.component';
import { ApplicationSubmittedComponent } from './components/application-submitted/application-submitted.component';


const routes: Routes = [
  {path: '', pathMatch: 'full', redirectTo: 'home'},
  {path: 'home', component: HomeComponent},
  {path: 'fosters', component: FosterListComponent},
  {path: 'volunteers', component: UserListComponent},
  {path: 'pet/:id', component: PetProfileComponent},
  {path: 'pet/search/:keyword', component: NavSearchComponent},
  {path: 'user/:id', component: UserProfileComponent},
  {path: 'shelters', component: ShelterListComponent},
  {path: 'shelter/:id', component: ShelterProfileComponent},
  {path: 'shelter', component: ShelterProfileComponent},
  {path: 'shelternav', component: ShelternavComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'pet', component: PetProfileComponent},
  {path: 'login', component: LoginComponent},
  {path: 'petsearch', component: PetSearchComponent},
  {path: 'appsubmitted', component: ApplicationSubmittedComponent},
  {path: 'admin', component: AdminPageComponent},
  {path: '**', component: NotFoundComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
