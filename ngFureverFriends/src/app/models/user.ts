import { Account } from './account';
import { Address } from './address';

export class User {
  // FIELDS
<<<<<<< HEAD
  private id: number;
  fname: string;
  private lname: string;
  private age: number;
  private phone: string;
  private email: string;
=======
  id: number;
  fname: string;
  lname: string;
  age: number;
  phone: string;
  email: string;
  account: Account;
  address: Address;
>>>>>>> b77c1f63a43005838f4190aabf1674c0b64e8276

  // CONSTRUCTOR
  constructor(
    id?: number,
    fname?: string,
    lname?: string,
    age?: number,
    phone?: string,
    email?: string,
    account?: Account,
    address?: Address
  ) {
    this.id = id;
    this.fname = fname;
    this.lname = lname;
    this.age = age;
    this.phone = phone;
    this.email = email;
    this.account = account;
    this.address = address;
  }
}
