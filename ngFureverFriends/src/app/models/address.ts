export class Address {
  id: number;
  street: string;
  street2: string;
  city: string;
  zip: number;
  stateAbbr: string;

  constructor(
    id?: number,
    street?: string,
    street2?: string,
    city?: string,
    zip?: number,
    stateAbbr?: string) {
    this.id = id;
    this.street = street;
    this.street2 = street2;
    this.city = city;
    this.zip = zip;
    this.stateAbbr = stateAbbr;
  }
}
