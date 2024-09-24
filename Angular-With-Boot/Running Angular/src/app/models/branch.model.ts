import { CompanyModel } from './company.model';
import { DepartmentModel } from './department.model';
import { UserModel } from './user.model';

export class BranchModel {
  id?: number;
  branchName!: string;
  address!: string;
  city!: string;
  zipCode!: string;
  country!: string;
  createdAt!: Date;
  updateAt!: Date;
  photo!: string;

  company!: CompanyModel;

  department!: DepartmentModel;

  users!: UserModel;
}
