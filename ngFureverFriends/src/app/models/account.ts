export class Account {
  id: number;
  username: string;
  password: string;
  role: string;
  active: boolean;

  constructor(
    id?: number,
    username?: string,
    password?: string,
    role?: string,
    active?: boolean) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.active = active;
  }
}
