export class Breed {
  id: number;
  name: string;
  hypoallergenic: boolean;
  hairType: string;
  description: number;
  size: string;

  constructor(
    id?: number,
    name?: string,
    hypoallergenic?: boolean,
    hairType?: string,
    description?: number,
    size?: string) {
    this.id = id;
    this.name = name;
    this.hypoallergenic = hypoallergenic;
    this.hairType = hairType;
    this.description = description;
    this.size = size;
  }
}
