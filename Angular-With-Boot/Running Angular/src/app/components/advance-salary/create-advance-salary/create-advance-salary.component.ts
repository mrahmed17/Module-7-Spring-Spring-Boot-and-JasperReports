import { Component, OnInit } from '@angular/core';
import { AdvanceSalaryModel } from '../../../models/advance-salary.model';
import { AdvanceSalaryService } from '../../../services/advancesalary.service';
import { MonthEnum } from '../../../models/month.enum';
import { NotificationService } from '../../../services/notification.service';
import { Router } from '@angular/router';
import { UserModel } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';

@Component({
  selector: 'app-create-advance-salary',
  templateUrl: './create-advance-salary.component.html',
  styleUrls: ['./create-advance-salary.component.css'],
})
export class CreateAdvanceSalaryComponent implements OnInit {
  advanceSalary: AdvanceSalaryModel = new AdvanceSalaryModel();
  months: string[] = [];
  users: UserModel[] = [];

  constructor(
    private advanceSalaryService: AdvanceSalaryService,
    private userService: UserService,
    private router: Router,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.months = Object.keys(MonthEnum).filter((key) => isNaN(Number(key)));

    this.userService.getAllUsers().subscribe((data) => {
      this.users = data;
    });
  }

  createAdvanceSalary(): void {
    this.advanceSalaryService
      .createAdvanceSalary(this.advanceSalary)
      .subscribe({
        next: (response) => {
          this.notificationService.showNotify(
            'Advance Salary Created successfully!',
            'success'
          );
          this.router.navigate(['/advance-salary/list']);
          console.log('Advance salary created successfully:', response);
        },
        error: (err) => {
          console.error('Error creating advance salary:', err);
          this.notificationService.showNotify(
            'Error creating advance salary!',
            'error'
          );
        },
      });
  }

  cancel(): void {
    this.router.navigate(['/advance-salary/list']);
  }
}
