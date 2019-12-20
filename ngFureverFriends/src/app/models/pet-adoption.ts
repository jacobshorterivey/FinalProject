export class PetAdoption {

  id: number;

  dateRequested: Date;

  acceptedDate: Date;

  meetDate: Date;

  meetReqDate: Date;

  meetNotes: string;

  reasonDenied: string;

  // Constructors

  constructor(id?: number, dateRequested?: Date, acceptedDate?: Date,
              meetDate?: Date, meetReqDate?: Date, meetNotes?: string,
              reasonDenied?: string) {
    this.id = id;
    this.dateRequested = dateRequested;
    this.acceptedDate = acceptedDate;
    this.meetDate = meetDate;
    this.meetReqDate = meetReqDate;
    this.meetNotes = meetNotes;
    this.reasonDenied = reasonDenied;

  }

}
