import { Shelter } from 'src/app/models/shelter';
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
      if (animal.breed.species.name === 'Cat') {
        result.push(animal);
        console.log(result);
      }

      return result;
    }
  }



//   transform(todos: Todo[], showAllTodos?: boolean): Todo[] {
//     const result: Todo[] = [];

//     if (showAllTodos === true) {
//       return todos;
//     }

//     for (const todo of todos) {
//       if (todo.completed !== true) {
//         result.push(todo);
//       }
//     }

//     return result;
//   }
// }
//   ng g pipe pipes/
}
