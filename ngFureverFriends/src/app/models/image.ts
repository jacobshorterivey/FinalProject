export class Image {

  id: number;
  imageUrl: string;

  // Constructor

  constructor(id?: number, imageUrl?: string) {
    this.id = id;
    this.imageUrl = imageUrl;
  }
}
