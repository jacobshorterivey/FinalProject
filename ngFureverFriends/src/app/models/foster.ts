import { User } from './user';

export class Foster {

  id: number;
  maxFoster: number;
  user: User;

  // Constructors
  constructor(id?: number, maxFoster?: number, user?: User) {
    this.id = id;
    this.maxFoster = maxFoster;
    this.user = user;
  }
}
