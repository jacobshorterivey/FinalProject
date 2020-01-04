// import { FilterPetsPipe } from './../../pipes/filter-pets.pipe';
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
  // petSpecies = 'all';
  newPet: Pet = new Pet();
  editPet: Pet = null;
  showDogs = true;
  showCats = true;
  isIntelligent = true;
  isPlayful = true;
  isGentle = true;
  isAlert = true;
  isConfident = true;
  isFaithful = true;
  isOutgoing = true;
  isCuddly = true;
  isReserved = true;
  isAffectionate = true;
  isQuiet = true;
  isAdventurous = true;
  public selected: Pet;

  constructor(private petService: PetService) { }

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

  openModule(animal: Pet) {
    this.selected = animal;
  }


}
