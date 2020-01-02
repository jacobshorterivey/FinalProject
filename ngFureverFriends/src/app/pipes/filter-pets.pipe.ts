import { Pipe, PipeTransform } from '@angular/core';
import { Pet } from '../models/pet';

@Pipe({
  name: 'filterPets'
})
export class FilterPetsPipe implements PipeTransform {

  transform(pets: Pet[], showDogs: boolean, showCats: boolean): any[] {
    console.log(showDogs + " " + showCats);
    if (showDogs === true && showCats === true) {
      return pets;
    }

    const results: Pet[] = [];
    if ( showDogs) {
    for (const pet of pets) {
      if (pet.breed.species.id === 1) {
        results.push(pet);
      }

    }

  }
    if ( showCats) {
    for (const pet of pets) {
      if (pet.breed.species.id === 2) {
        results.push(pet);
      }

    }

  }


    return results;

  }
}
