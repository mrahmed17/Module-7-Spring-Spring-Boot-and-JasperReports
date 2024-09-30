import { BranchModel } from './branch.model';
import { CompanyModel } from './company.model';
import { UserModel } from './user.model';

export class DepartmentModel {
  id!: number;
  departmentName!: string;
  numOfEmployees!: number;
  photo!: string;

  branch!: BranchModel;

  // companies!: CompanyModel;

  users!: UserModel;
}
