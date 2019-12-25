import { HideDogPipe } from './../../pipes/hide-dog.pipe';
import { PetService } from './../../services/pet.service';
import { Component, OnInit } from '@angular/core';
import { Pet } from 'src/app/models/pet';

@Component({
  selector: 'app-pet-search',
  templateUrl: './pet-search.component.html',
  styleUrls: ['./pet-search.component.css']
})
export class PetSearchComponent implements OnInit {
  pets: Pet[] = [];

  newPet: Pet = new Pet();
  editPet: Pet = null;
  showDogs = true;
  showCats = true;

  constructor(private petService: PetService, private hideDog: HideDogPipe) { }

  ngOnInit() {
    this.getPets();
  }

  getPets() {

    this.petService.index().subscribe(
      data => {
        this.pets = data;
      },
      err => {
        console.error(err);
      }
    );
  }

  hideDogs() {
    // const catList = this.hideDog.transform(this.pets);
    const catList = this.hideDog.transform(this.pets);
    console.log(catList);
    // return catList;
  }

}
