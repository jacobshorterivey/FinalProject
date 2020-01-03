import { ShelterService } from 'src/app/services/shelter.service';
import { Shelter } from 'src/app/models/shelter';
import { PetService } from './../../services/pet.service';
import { Pet } from './../../models/pet';
import { Component, OnInit } from '@angular/core';
import { subscribeOn } from 'rxjs/operators';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from 'src/app/models/account';

@Component({
  selector: 'app-pet-profile',
  templateUrl: './pet-profile.component.html',
  styleUrls: ['./pet-profile.component.css']
})
export class PetProfileComponent implements OnInit {

  // Field
  title: 'Pet Tracker';
  newAnimal: Pet = new Pet();
  pets: Pet[] = [];
  selectedPet: Pet = null;
  editPet: Pet = null;
  shelter: Shelter;
  account: Account;


  // Constructor
  constructor(private petService: PetService, private route: ActivatedRoute, private router: Router, private shelterSVC: ShelterService) { }

  // Methods
  ngOnInit() {
    this.shelterSVC.index().subscribe(
      data => {
        this.account = JSON.parse(localStorage.getItem('account'));
        data.forEach(element => {
          if (element.account.id === this.account.id) {
            this.loadShelterPets(element.id);
          }
        });
      },
      err => {
        console.error(err);
        console.error('TodoListCompenent.loadtodoList():error loading todos');
      }

    );
    // if (!this.selectedPet && this.route.snapshot.paramMap.get('id')) {
    //   this.petService.showOne(this.route.snapshot.paramMap.get('id')).subscribe(
    //     data => {
    //       this.selectedPet = data;
    //       if (this.selectedPet === null) {
    //         this.router.navigateByUrl('pet' + this.route.snapshot.paramMap.get('id') + 'NotFound');
    //       }
    //     },
    //     err => console.error('failed to find a single pet')
    //   );
    // }
  }

  addPet(newPet: Pet) {
    this.petService.create(newPet).subscribe(
      data => {
        console.log(data);
        this.newAnimal = new Pet();
        this.loadShelterPets(this.shelter.id);
    },
      err => {
        console.error(err);
      }
    );
    // this.reload();
  }

  updatePet() {
    this.petService.update(this.selectedPet.id, this.selectedPet).subscribe(
      data => {
        console.log(data);
        // this.reload();
      },
      err => {
        console.error(err);
      }
    );
    // this.reload();
  }

  deletePet(id: number) {
    this.petService.destroy(id).subscribe(
      (data) => {
        this.loadShelterPets(this.shelter.id);
      },
        err => {
          console.error(err);
        }
    );
  }

  loadShelterPets(id: number) {
    this.shelterSVC.findPets(id).subscribe(
      (pass) => {
        this.pets = pass;
      },
      (fail) => {
        console.error(fail);
      }
    );
  }
}

