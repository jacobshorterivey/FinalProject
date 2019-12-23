import { ShelterService } from './services/shelter.service';
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
import { FormsModule } from '@angular/forms';
import { ShelternavComponent } from './components/shelternav/shelternav.component';
import { FosterListComponent } from './components/foster-list/foster-list.component';
import { DogFilterPipe } from './dog-filter.pipe';
import { RouterModule } from '@angular/router';

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
    UserListComponent,
    ShelternavComponent,
    FosterListComponent,
    DogFilterPipe
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [
    ShelterService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
