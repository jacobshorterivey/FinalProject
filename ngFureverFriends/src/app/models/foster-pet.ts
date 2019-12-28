export class FosterPet {
  id: number;
  notes?: string;
  active?: boolean;
  dateRequested?: Date;
  dateCompleted?: Date;

  constructor(
    id?: number,
    notes?: string,
    active?: boolean,
    dateRequested?: Date,
    dateCompleted?: Date) {
    this.id = id;
    this.notes = notes;
    this.active = active;
    this.dateRequested = dateRequested;
    this.dateCompleted = dateCompleted;
  }
}
