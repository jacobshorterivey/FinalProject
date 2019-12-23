import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dogFilter'
})
export class DogFilterPipe implements PipeTransform {

  transform(value: any, ...args: any[]): any {
    return null;
  }

}
