import { Image } from './image';
import { Trait } from './trait';
import { Shelter } from 'src/app/models/shelter';
import { Breed } from './breed';

export class Pet {

  id: number;
  color: string;
  name: string;
  size: string;
  age: number;
  weight: number;
  adopted: boolean;
  fixed: boolean;
  specialConditions: string;
  shelter: Shelter;
  breed: Breed;
  traits: Trait[];
  images: Image[];

  constructor(id?: number, color?: string,
              name?: string, size?: string, age?: number, weight?: number,
              adopted?: boolean, fixed?: boolean, specialConditions?: string,
              shelter?: Shelter, breed?: Breed, traits?: Trait[], images?: Image[]
              ) {
    this.id = id;
    this.color = color;
    this.name = name;
    this.size = size;
    this.age = age;
    this.weight = weight;
    this.adopted = adopted;
    this.fixed = fixed;
    this.specialConditions = specialConditions;
    this.shelter = shelter;
    this.breed = breed;
    this.traits = traits;
    this.images = images;
  }

}
