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

  constructor(id?: number, color?: string,
    name?: string, size?: number, age?: number, weight?: number,
    adopted?: boolean, fixed?: boolean, specialConditions?: string) {
    this.id = id;
    this.color = color;
    this.name = name;
    this.size = size;
    this.age = age;
    this.weight = weight;
    this.adopted = adopted;
    this.fixed = fixed;
    this.specialConditions = specialConditions;

  }

}
