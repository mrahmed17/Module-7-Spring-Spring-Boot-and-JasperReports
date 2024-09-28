

import { UserModel } from "./user.model";

export class AttendanceModel {
 id?: number;
 
  date!: Date;
  clockInTime!: Date;
  clockOutTime!: Date;

  late!: boolean;

  user!: UserModel;
}


