import { Account } from './account';
import { Address } from './address';

export class Shelter {

  id: number;
  phone: string;
  email: string;
  name: string;
  account: Account;
  address: Address;

  // Constructor
  constructor(id?: number, phone?: string, email?: string, name?: string, account?: Account, address?: Address) {
    this.id = id;
    this.phone = phone;
    this.email = email;
    this.name = name;
    this.account = account;
    this.address = address;
  }
}
