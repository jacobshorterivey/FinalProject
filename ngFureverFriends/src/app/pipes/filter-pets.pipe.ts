import { Pipe, PipeTransform } from '@angular/core';
import { Pet } from '../models/pet';

@Pipe({
  name: 'filterPets'
})
export class FilterPetsPipe implements PipeTransform {

  transform(pets: Pet[], showDogs: boolean, showCats: boolean, isIntelligent: boolean, isPlayful: boolean,
            isGentle: boolean): any[] {

    const results: Pet[] = [];
    if (isIntelligent || isPlayful || isGentle) {
      for (const pet of pets) {
        if (isIntelligent) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 1) {
              if (showCats && pet.breed.species.id === 2) {
                results.push(pet);
              } else if (showDogs && pet.breed.species.id === 1) {
                results.push(pet);
              }
            }
          }
        }
        if (isPlayful) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 2) {
              if (results.includes(pet)) {
                // continue;
              } else {
                if (showCats && pet.breed.species.id === 2) {
                  results.push(pet);
                } else if (showDogs && pet.breed.species.id === 1) {
                  results.push(pet);
                }
              }
            }
          }
        }
      }
      return results;
    }
    if (showDogs) {
      for (const pet of pets) {
        if (pet.breed.species.id === 1) {
          results.push(pet);
        }

      }

    }
    if (showCats) {
      for (const pet of pets) {
        if (pet.breed.species.id === 2) {
          results.push(pet);
        }

      }

    }



    if (showDogs === true && showCats === true) {
      return pets;
    }
    return results;


  }
}

// Intelligent
// Playful
// Gentle
// Alert
// Confident
// Faithful
// Outgoing
// Cuddly
// Reserved
// Affectionate
// Quiet
// Adventurous
