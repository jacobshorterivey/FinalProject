import { HttpClientModule, HttpClient } from '@angular/common/http';
import { Component, OnInit, createPlatform, ChangeDetectorRef } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Account } from 'src/app/models/account';
import { Breed } from 'src/app/models/breed';
import { Shelter } from 'src/app/models/shelter';
import { ShelterService } from 'src/app/services/shelter.service';
import { Pet } from './../../models/pet';
import { PetService } from './../../services/pet.service';
import { Trait } from 'src/app/models/trait';
import { Image } from 'src/app/models/image';

@Component({
  selector: 'app-pet-profile',
  templateUrl: './pet-profile.component.html',
  styleUrls: ['./pet-profile.component.css']
})
export class PetProfileComponent implements OnInit {

  // Field
  title: 'Pet Tracker';
  createPet: Pet = new Pet();
  newAnimal: Pet = new Pet();
  pets: Pet[] = [];
  selectedPet: Pet = null;
  editPet: Pet = null;
  shelter: Shelter;
  account: Account;
  create = false;
  dogCreate = false;
  catCreate = false;
  updatePetMessage: boolean;
  breedList: Breed[] = [];
  traitList: Trait[] = [];
  traitsToAdd: Trait[] = [];
  image: Image = new Image();
  /// EXPERIMENTING
  selectedFile: File = null;
  traitBoolean = [
    {
      id: 1,
      active: false
    },
    {
      id: 2,
      active: false
    },
    {
      id: 3,
      active: false
    },
    {
      id: 4,
      active: false
    },
    {
      id: 5,
      active: false
    },
    {
      id: 6,
      active: false
    },
    {
      id: 7,
      active: false
    },
    {
      id: 8,
      active: false
    },
    {
      id: 9,
      active: false
    },
    {
      id: 10,
      active: false
    },
    {
      id: 11,
      active: false
    },
    {
      id: 12,
      active: false
    },

  ];


  // Constructor
  constructor(private petService: PetService, private route: ActivatedRoute, private router: Router, private shelterSVC: ShelterService,
              private cdRef: ChangeDetectorRef, private http: HttpClient) { }

  // Methods
  ngOnInit() {
    this.createPet.breed = new Breed();
    this.shelterSVC.index().subscribe(
      data => {
        this.account = JSON.parse(localStorage.getItem('account'));
        data.forEach(element => {
          if (element.account.id === this.account.id) {
            this.loadShelterPets(element.id);
            this.shelter = element;
          }
        });
      },
      err => {
        console.error(err);
        console.error('TodoListCompenent.loadtodoList():error loading todos');
      }

    );

    this.loadBreedAndTraitLists();
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

  addPet() {
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.traitBoolean.length; i++) {
      const bool = this.traitBoolean[i];
      // tslint:disable-next-line: prefer-for-of
      for (let k = 0; k < this.traitList.length; k++) {
        const trait = this.traitList[k];
        if ((trait.id === bool.id) && bool.active) {
          this.traitsToAdd.push(trait);
          bool.active = false;
        }
      }
    }

    this.createPet.adopted = false;
    this.createPet.traits = this.traitsToAdd;
    this.createPet.shelter = this.shelter;
    this.createPet.images.push(this.image);

    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.breedList.length; i++) {
      const breed = this.breedList[i];
      // tslint:disable-next-line: triple-equals
      if (breed.id == this.createPet.breed.id) {
        this.createPet.breed = breed;
        i = this.breedList.length;
      }
    }

    this.petService.create(this.createPet).subscribe(
      data => {
        console.log(data);
        this.newAnimal = new Pet();
        this.createPet = new Pet();
        this.loadShelterPets(this.shelter.id);
        this.dogCreate = false;
        this.catCreate = false;
        this.create = false;
        // window.location.reload();
      },
      err => {
        console.error(err);
      }
    );

    this.loadBreedAndTraitLists();
  }
  updatePet() {
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < this.traitBoolean.length; i++) {
      const bool = this.traitBoolean[i];
      // tslint:disable-next-line: prefer-for-of
      for (let k = 0; k < this.traitList.length; k++) {
        const trait = this.traitList[k];
        if ((trait.id === bool.id) && bool.active) {
          this.traitsToAdd.push(trait);
          bool.active = false;
        }
      }
    }

    if (this.traitsToAdd !== null || this.traitsToAdd !== undefined) {
      this.selectedPet.traits = this.traitsToAdd;
    }

    this.petService.update(this.selectedPet.id, this.selectedPet).subscribe(
      data => {
        console.log(data);
        this.updatePetMessage = false;
        this.cdRef.detectChanges();
        this.updatePetMessage = true;
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
        this.selectedPet = null;
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

  loadBreedAndTraitLists() {
    this.petService.indexBreed().subscribe(
      data => {
        this.breedList = data;
      },
      error => {
        console.log('pet-profile.ts: loadBreedAndTraitLists(): Error getting breeds');
        console.log(error);
      }
    );

    this.petService.indexTrait().subscribe(
      data => {
        this.traitList = data;
      },
      error => {
        console.log('pet-profile.ts: loadBreedAndTraitLists(): Error getting traits');
        console.log(error);
      }
    );
  }





  onFileSelected(event) {
    console.log(event);
    this.selectedFile = event.target.files[0] as File;
    console.log(this.selectedFile);

    const fd = new FormData();
    fd.append('image', this.selectedFile, this.selectedFile.name);

    this.http.post<ImageResponse>('https://api.imgbb.com/1/upload?key=5eaa21d03c3d50fc34483bccfbea594f', fd).subscribe(
      res => {
        console.log(res);
        console.log(res.data.url);
      }
    );
  }
}

interface ImageResponse {
  data: any;
}

