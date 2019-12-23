import { Species } from './species';
export class Breed {
  id: number;
  name: string;
  hypoallergenic: boolean;
  hairType: string;
  description: number;
  size: string;
  species: Species;

  constructor(
    id?: number,
    name?: string,
    hypoallergenic?: boolean,
    hairType?: string,
    description?: number,
    size?: string,
    species?: Species) {
    this.id = id;
    this.name = name;
    this.hypoallergenic = hypoallergenic;
    this.hairType = hairType;
    this.description = description;
    this.size = size;
    this.species = species;
  }
}
