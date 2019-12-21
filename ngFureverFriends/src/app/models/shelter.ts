import { Address } from './address';
import { Image } from './image';
import { Pet } from './pet';
export class Shelter {

  id: number;
  phone: string;
  email: string;
  name: string;
  address: Address;
  websiteUrl: string;
  pets: Pet[];
  images: Image[];
  account: Account;

  // Constructor
  constructor(id?: number, phone?: string, email?: string, name?: string, websiteUrl?: string,
              address?: Address, pets?: Pet[], images?: Image[], account?: Account) {
    this.id = id;
    this.phone = phone;
    this.email = email;
    this.name = name;
    this.address = address;
    this.websiteUrl = websiteUrl;
    this.address = address;
    this.pets = pets;
    this.images = images;
    this.account = account;
  }
}
