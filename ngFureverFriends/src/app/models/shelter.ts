import { Address } from './address';
import { Image } from './image';
import { Pet } from './pet';
export class Shelter {

  id: number;
  phone: string;
  email: string;
  name: string;
  websiteUrl: string;
  address: Address;
  pets: Pet[];
  images: Image[];

  // Constructor
  constructor(id?: number, phone?: string, email?: string, name?: string, websiteUrl?: string,
              address?: Address, pets?: Pet[], images?: Image[]) {
    this.id = id;
    this.phone = phone;
    this.email = email;
    this.name = name;
    this.websiteUrl = websiteUrl;
    this.address = address;
    this.pets = pets;
    this.images = images;
  }
}
