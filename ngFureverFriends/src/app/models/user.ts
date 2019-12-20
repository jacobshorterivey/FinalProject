export class User {

  // FIELDS
  private id: number;
  private fname: string;
  private lname: string;
  private age: number;
  private phone: string;
  private email: string;

  // CONSTRUCTOR
  constructor(id?: number, fname?: string, lname?: string, age?: number, phone?: string, email?: string) {
    this.id = id;
    this.fname = fname;
    this.lname = lname;
    this.age = age;
    this.phone = phone;
    this.email = email;
  }
}
