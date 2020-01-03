import { Breed } from './breed';
import { User } from './user';

export class Foster {

  id: number;
  maxFoster: number;
  user: User;
  breedList: Breed[];
  fosterPets: [];

  // Constructors
  constructor(id?: number, maxFoster?: number, user?: User, breedList?: Breed[], fosterPets?: []) {
    this.id = id;
    this.maxFoster = maxFoster;
    this.user = user;
    this.breedList = breedList;
    this.fosterPets = fosterPets;
  }
}
