export class EmailServiceImpl {
  toAddress: string;
  subject: string;
  body: string;

  constructor(toAddress: string, subject: string, body: string) {
    this.toAddress = toAddress;
    this.subject = subject;
    this.body = body;
  }
}
