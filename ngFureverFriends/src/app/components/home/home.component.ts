import { PetService } from './../../services/pet.service';
import { Component, OnInit } from '@angular/core';
import { Pet } from 'src/app/models/pet';
import { Shelter } from 'src/app/models/shelter';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  // FIELDS
  dogs: Pet[] = [];
  cats: Pet[] = [];
  selected: Pet;
  shelter: Shelter[] = [];
  shelterCat: Shelter[] = [];

  // CONSTRUCTORS
  constructor(private petSvc: PetService) { }

  // METHODS
  ngOnInit() {
    this.pickBackgroundImg();
    this.loadPets();
  }

  pickBackgroundImg() {
    const num = Math.floor(Math.random() * 4);
    const myRow = document.querySelector('.openingDiv');
    if (num === 0) {
      myRow.setAttribute('style', 'background-image: url("../../../assets/img/bgimg1.jpg");');
    } else if (num === 1) {
      myRow.setAttribute('style', 'background-image: url("../../../assets/img/bgimg2.jpg");');
    } else if (num === 2) {
      myRow.setAttribute('style', 'background-image: url("../../../assets/img/bgimg3.jpg");');
    } else if (num === 3) {
      myRow.setAttribute('style', 'background-image: url("../../../assets/img/bgimg4.jpg");');
    }
    console.log(num);
  }

  loadPets() {
    this.petSvc.index().subscribe(
      (pass) => {
        pass.forEach(pet => {
          if (pet.adopted === false && pet.breed.species.name === 'Dog' && this.dogs.length < 6) {
            this.dogs = this.dogs.concat(pet);
            this.petSvc.getShelter(pet.id).subscribe(
              (win) => {
                this.shelter = this.shelter.concat(win);
              },
              (nowin) => {
                console.error(nowin);
              }
            );
          }
          if (pet.adopted === false && pet.breed.species.name === 'Cat' && this.dogs.length < 6) {
            this.cats = this.cats.concat(pet);
            this.petSvc.getShelter(pet.id).subscribe(
              (win) => {
                this.shelterCat = this.shelterCat.concat(win);
              },
              (nowin) => {
                console.error(nowin);
              }
            );
            }
        });
      },
      (fail) => {
        console.error(fail);
      }
    );
  }

  scrollToPets() {
      const element = document.querySelector('#featStart');
      element.scrollIntoView({behavior: 'smooth'});
  }

  openModule(animal: Pet) {
    this.selected = animal;
  }

  // getShelter(id: number) {
  //   this.petSvc.getShelter(id).subscribe(
  //     (pass) => {
  //       this.shelter = pass;
  //     },
  //     (fail) => {
  //       console.error(fail);
  //     }
  //   );
  // }

}
