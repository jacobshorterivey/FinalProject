import { Pipe, PipeTransform } from '@angular/core';
import { Pet } from '../models/pet';

@Pipe({
  name: 'filterPets'
})
export class FilterPetsPipe implements PipeTransform {

  transform(pets: Pet[], showDogs: boolean, showCats: boolean, isIntelligent: boolean, isPlayful: boolean,
            isGentle: boolean, isAlert: boolean, isConfident: boolean, isFaithful: boolean, isOutgoing: boolean, isCuddly: boolean,
            isReserved: boolean, isAffectionate: boolean, isQuiet: boolean, isAdventurous: boolean): any[] {

    const results: Pet[] = [];
    if (isIntelligent || isPlayful || isGentle || isAlert || isConfident || isFaithful || isOutgoing || isCuddly
      || isReserved || isAffectionate || isQuiet || isAdventurous) {
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
        if (isGentle) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 3) {
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
        if (isAlert) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 4) {
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
        if (isConfident) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 5) {
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
        if (isFaithful) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 6) {
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
        if (isOutgoing) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 7) {
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
        if (isCuddly) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 8) {
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
        if (isReserved) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 9) {
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
        if (isAffectionate) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 10) {
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
        if (isQuiet) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 11) {
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
        if (isAdventurous) {
          // tslint:disable-next-line: prefer-for-of
          for (let i = 0; i < pet.traits.length; i++) {
            if (pet.traits[i].id === 12) {
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
