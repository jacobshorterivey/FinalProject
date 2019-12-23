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

  selectedPet: Pet = null;
  editPet: Pet = null;

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
}
