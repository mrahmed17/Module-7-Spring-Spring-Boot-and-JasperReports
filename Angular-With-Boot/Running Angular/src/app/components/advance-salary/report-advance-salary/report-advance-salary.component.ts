import { Component, OnInit } from '@angular/core';
import { AdvanceSalaryService } from '../../../services/advancesalary.service';
import { AdvanceSalaryModel } from '../../../models/advance-salary.model';
import { NotificationService } from '../../../services/notification.service';

@Component({
  selector: 'app-report-advance-salary',
  templateUrl: './report-advance-salary.component.html',
  styleUrls: ['./report-advance-salary.component.css'],
})
export class ReportAdvanceSalaryComponent implements OnInit {
  advanceSalaries: AdvanceSalaryModel[] = [];
  totalAdvancePaid: number = 0;
  totalEntries: number = 0;

  constructor(
    private advanceSalaryService: AdvanceSalaryService,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.loadAdvanceSalaryReport();
  }

  loadAdvanceSalaryReport(): void {
    this.advanceSalaryService.getAllAdvanceSalaries().subscribe({
      next: (response: AdvanceSalaryModel[]) => {
        this.advanceSalaries = response;
        this.calculateTotals();
      },
      error: (err) => {
        console.error('Error fetching advance salary report:', err);
        this.notificationService.showNotify(
          'Error fetching advance salary report!',
          'error'
        );
      },
    });
  }

  calculateTotals(): void {
    this.totalEntries = this.advanceSalaries.length;
    this.totalAdvancePaid = this.advanceSalaries.reduce(
      (sum, salary) => sum + salary.advanceSalary,
      0
    );
  }


  getTotalSalaryByName(userName: string): number {
    return this.advanceSalaries
      .filter(
        (salary) =>
          salary.user.fullName.toLowerCase() === userName.toLowerCase()
      )
      .reduce((sum, salary) => sum + salary.advanceSalary, 0);
  }

  getTotalSalaryByDateRange(startDate: Date, endDate: Date): number {
    return this.advanceSalaries
      .filter(
        (salary) =>
          new Date(salary.advanceDate).getTime() >= startDate.getTime() &&
          new Date(salary.advanceDate).getTime() <= endDate.getTime()
      )
      .reduce((sum, salary) => sum + salary.advanceSalary, 0);
  }


  generateReport(): void {
    console.log('Generating report for advance salaries...');
  }

  // Additional methods can be added here for further reporting needs
}
