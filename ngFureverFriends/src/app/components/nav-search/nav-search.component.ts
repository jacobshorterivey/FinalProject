import { Component, OnInit } from '@angular/core';
import { PetService } from './../../services/pet.service';
import { Pet } from 'src/app/models/pet';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-nav-search',
  templateUrl: './nav-search.component.html',
  styleUrls: ['./nav-search.component.css']
})
export class NavSearchComponent implements OnInit {

  searchedPets: Pet[] = [];
  // searchKey: string = null;

  constructor(
    private petService: PetService,
    private route: ActivatedRoute
  ) { }

  ngOnInit() {
    console.log('Nav search page!');
    if (this.route.snapshot.paramMap.get('keyword')) {

    }

    if (this.route.snapshot.paramMap.get('keyword')) {
      const keyword = this.route.snapshot.paramMap.get('keyword');
      this.searchForPets(keyword);
      console.log(this.searchedPets);
    } else {
      // window.location.reload();
    }
    this.loadEvents();
  }

  loadEvents() {
    // this.svc.index().subscribe(
    //   (pass) => {
    //     this.shelters = pass;
    //   },
    //   (fail) => {
    //     console.error(fail);
    //   }
    // );
  }

  searchForPets(keyword: string) {
    console.log('in searchForPets()');
    this.petService.nameTraitsBreedSearch(keyword).subscribe(
      data => {
        this.searchedPets = data;
      },
      err => {
        console.error(err);
      }
    );
  }
}
