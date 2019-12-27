import { Pipe, PipeTransform } from '@angular/core';
import { Pet } from '../models/pet';

@Pipe({
  name: 'hideDog'
})
export class HideDogPipe implements PipeTransform {

  transform(pets: Pet[], hideDogs?: boolean): Pet[] {
    const result: Pet[] = [];

    if (hideDogs === true) {
      return pets;
    }

    for (const animal of pets) {
      if (animal.breed.species.id === 1) {
          // name.toLowerCase() === 'cat') {
        result.push(animal);
      }

      return result;
    }
  }

}
