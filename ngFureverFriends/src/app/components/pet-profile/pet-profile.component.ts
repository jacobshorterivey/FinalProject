import { PetService } from './../../services/pet.service';
import { Pet } from './../../models/pet';
import { Component, OnInit } from '@angular/core';
import { subscribeOn } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-pet-profile',
  templateUrl: './pet-profile.component.html',
  styleUrls: ['./pet-profile.component.css']
})
export class PetProfileComponent implements OnInit {

  // Field
  title: 'Pet Tracker';
  newPet: Pet = new Pet();
  pets: Pet[] = [];
  selectedPet: Pet = null;
  editPet: Pet = null;


  // Constructor
  constructor(private petService: PetService, private route: ActivatedRoute, private router: Router) { }

  // Methods
  ngOnInit() {
    if (!this.selectedPet && this.route.snapshot.paramMap.get('id')) {
      this.petService.showOne(this.route.snapshot.paramMap.get('id')).subscribe(
        data => {
          this.selectedPet = data;
          if (this.selectedPet === null) {
            this.router.navigateByUrl('pet' + this.route.snapshot.paramMap.get('id') + 'NotFound');
          }
        },
        err => console.error('failed to find a single pet')
      );
    }
    this.reload();
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
  addPet() {
    this.petService.create(this.newPet).subscribe(
      data => {
        console.log(data);
        this.reload();
        this.newPet = new Pet();
    },
      err => {
        console.error(err);
      }
    );
    this.reload();
  }
  updatePet() {
    this.petService.update(this.selectedPet.id, this.selectedPet).subscribe(
      data => {
        console.log(data);
        this.reload();
      },
      err => {
        console.error(err);
      }
    );
    this.reload();
  }
  deletePet(id: number) {
    this.petService.destroy(id).subscribe(
      data => {
        console.log(data);
        this.reload();
      },
        err => {
          console.error(err);
        }
    );
  }
  reload() {
    this.petService.index().subscribe(
      (aGoodThingHappened) => {
        console.log(aGoodThingHappened);
        this.pets = aGoodThingHappened;
      },

      (didntWork) => {
        console.log(didntWork);
      }
    );
  }
  countPets() {
    return this.pets.length;
  }
}
