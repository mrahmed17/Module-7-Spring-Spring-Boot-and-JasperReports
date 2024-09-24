import { BranchModel } from './branch.model';
import { DepartmentModel } from './department.model';
import { UserModel } from './user.model';

export class CompanyModel {
  id?: number;
  companyName!: string;
  photo!: string;

  branches!: BranchModel;

  departments!: DepartmentModel;

  users!: UserModel;
}
