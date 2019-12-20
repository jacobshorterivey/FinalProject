export class Shelter {

  id: number;
  phone: string;
  email: string;
  name: string;

  // Constructor
  constructor(id?: number, phone?: string, email?: string, name?: string) {
    this.id = id;
    this.phone = phone;
    this.email = email;
    this.name = name;
  }
}
