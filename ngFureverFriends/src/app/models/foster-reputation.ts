export class FosterReputation {
  id: number;
  content: string;
  rating: number;

  constructor(
    id?: number,
    content?: string,
    rating?: number) {
    this.id = id;
    this.content = content;
    this.rating = rating;
  }
}
