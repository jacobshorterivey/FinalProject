import { Shelter } from 'src/app/models/shelter';
import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'hideDog'
})
export class HideDogPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {

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
  // ng g pipe pipes/
}
