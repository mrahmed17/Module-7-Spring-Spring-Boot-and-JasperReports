import { AdvanceSalaryModel } from './advance-salary.model';
import { BonusModel } from './bonus.model';
import { FeedbackModel } from './feedback.model';
import { LeaveModel } from './leave.model';
import { PaymentReceiptModel } from './payment-receipt.model';
import { RoleEnum } from './role.enum';
import { SalaryModel } from './salary.model';

export class UserModel {
  id!: number;
  fullName!: string;
  email!: string;
  password!: string;
  address?: string;
  gender!: string;
  dateOfBirth?: Date;
  nationalId?: string;
  contact?: string;
  basicSalary?: number;
  joinedDate?: Date;
  profilePhoto?: string;
  isActive!: boolean;
  updatedAt!: Date;

  role!: RoleEnum;
  advanceSalaries?: AdvanceSalaryModel[];
  bonuses?: BonusModel[];
  feedbacks?: FeedbackModel[];
  leaves?: LeaveModel[];
  salaries?: SalaryModel[];
  paymentReceivers?: PaymentReceiptModel[];
  paymentReceipts?: PaymentReceiptModel[];
}
