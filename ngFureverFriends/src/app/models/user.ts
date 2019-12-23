import { Account } from './account';
import { Address } from './address';
import { Skill } from './skill';

export class User {
  // FIELDS
  id: number;
  fname: string;
  lname: string;
  age: number;
  phone: string;
  email: string;
  account: Account;
  address: Address;

  skills: Skill[];


  // CONSTRUCTOR
  constructor(
    id?: number,
    fname?: string,
    lname?: string,
    age?: number,
    phone?: string,
    email?: string,
    account?: Account,
    address?: Address,
    skills?: Skill[]
  ) {
    this.id = id;
    this.fname = fname;
    this.lname = lname;
    this.age = age;
    this.phone = phone;
    this.email = email;
    this.account = account;
    this.address = address;
    this.skills = skills;
  }
}
