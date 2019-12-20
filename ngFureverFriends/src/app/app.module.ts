import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NavbarComponent } from './components/navbar/navbar.component';
import { PetProfileComponent } from './components/pet-profile/pet-profile.component';
import { ShelterProfileComponent } from './components/shelter-profile/shelter-profile.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { HomeComponent } from './components/home/home.component';
import { SiteResourcesComponent } from './components/site-resources/site-resources.component';
import { LoginComponent } from './components/login/login.component';
import { LogoutComponent } from './components/logout/logout.component';
import { RegisterComponent } from './components/register/register.component';
import { ShelterListComponent } from './components/shelter-list/shelter-list.component';
import { PetListComponent } from './components/pet-list/pet-list.component';
import { PetSearchComponent } from './components/pet-search/pet-search.component';
import { UserListComponent } from './components/user-list/user-list.component';

@NgModule({
  declarations: [
    AppComponent,
    NavbarComponent,
    PetProfileComponent,
    ShelterProfileComponent,
    UserProfileComponent,
    NotFoundComponent,
    HomeComponent,
    SiteResourcesComponent,
    LoginComponent,
    LogoutComponent,
    RegisterComponent,
    ShelterListComponent,
    PetListComponent,
    PetSearchComponent,
    UserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
