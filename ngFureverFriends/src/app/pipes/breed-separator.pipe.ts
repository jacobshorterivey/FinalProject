import { Pipe, PipeTransform } from '@angular/core';
import { Breed } from '../models/breed';

@Pipe({
  name: 'breedSeparator'
})
export class BreedSeparatorPipe implements PipeTransform {

  transform(breedList: Breed[], species: boolean): Breed[] {
    const result: Breed[] = [];
    if (species) {
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < breedList.length; i++) {
        const breed = breedList[i];
        if (breed.species.id === 1) {
          result.push(breed);
        }
      }
      return result;
    } else if (!species) {
      // tslint:disable-next-line: prefer-for-of
      for (let i = 0; i < breedList.length; i++) {
        const breed = breedList[i];
        if (breed.species.id === 2) {
          result.push(breed);
        }
      }
      return result;
    }
    return null;
  }

}
