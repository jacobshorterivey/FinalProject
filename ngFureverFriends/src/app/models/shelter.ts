<<<<<<< HEAD
import { Account } from './account';
import { Address } from './address';

=======
import { Address } from './address';
import { Image } from './image';
import { Pet } from './pet';
>>>>>>> dc9330ed2c61576340ca9f826d7b6b36f090cdbf
export class Shelter {

  id: number;
  phone: string;
  email: string;
  name: string;
<<<<<<< HEAD
  account: Account;
  address: Address;

  // Constructor
  constructor(id?: number, phone?: string, email?: string, name?: string, account?: Account, address?: Address) {
=======
  websiteUrl: string;
  address: Address;
  pets: Pet[];
  images: Image[];

  // Constructor
  constructor(id?: number, phone?: string, email?: string, name?: string, websiteUrl?: string,
              address?: Address, pets?: Pet[], images?: Image[]) {
>>>>>>> dc9330ed2c61576340ca9f826d7b6b36f090cdbf
    this.id = id;
    this.phone = phone;
    this.email = email;
    this.name = name;
<<<<<<< HEAD
    this.account = account;
    this.address = address;
=======
    this.websiteUrl = websiteUrl;
    this.address = address;
    this.pets = pets;
    this.images = images;
>>>>>>> dc9330ed2c61576340ca9f826d7b6b36f090cdbf
  }
}
