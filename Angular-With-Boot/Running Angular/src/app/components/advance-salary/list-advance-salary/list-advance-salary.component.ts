import { Component, OnInit } from '@angular/core';
import { AdvanceSalaryService } from '../../../services/advancesalary.service';
import { AdvanceSalaryModel } from '../../../models/advance-salary.model';
import { NotificationService } from '../../../services/notification.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-advance-salary',
  templateUrl: './list-advance-salary.component.html',
  styleUrls: ['./list-advance-salary.component.css'],
})
export class ListAdvanceSalaryComponent implements OnInit {
  advanceSalaries: AdvanceSalaryModel[] = [];
  filteredAdvanceSalaries: AdvanceSalaryModel[] = [];
  searchTerm: string = '';
  totalSalary: number = 0;

  constructor(
    private advanceSalaryService: AdvanceSalaryService,
    private notificationService: NotificationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loadAdvanceSalaries();
  }

  loadAdvanceSalaries(): void {
    this.advanceSalaryService.getAllAdvanceSalaries().subscribe({
      next: (data) => {
        this.advanceSalaries = data;
        this.filteredAdvanceSalaries = data; 
        this.calculateTotalSalary(); 
      },
      error: (err) => {
        console.error('Error loading advance salaries:', err);
        this.notificationService.showNotify(
          'Error loading advance salaries!',
          'error'
        );
      },
    });
  }

  filterAdvanceSalaries(): void {
    const searchTermLower = this.searchTerm.toLowerCase();
    this.filteredAdvanceSalaries = this.advanceSalaries.filter(
      (advanceSalary) =>
        advanceSalary.user.fullName.toLowerCase().includes(searchTermLower) ||
        advanceSalary.user.email.toLowerCase().includes(searchTermLower) ||
        advanceSalary.reason.toLowerCase().includes(searchTermLower) ||
        advanceSalary.advanceMonth
          .toString()
          .toLowerCase()
          .includes(searchTermLower) ||
        advanceSalary.year.toString().includes(searchTermLower)
    );
  }

  calculateTotalSalary(): void {
    this.totalSalary = this.filteredAdvanceSalaries.reduce(
      (sum, salary) => sum + salary.advanceSalary,
      0
    );
  }

  deleteAdvanceSalary(id: number): void {
    if (confirm('Are you sure you want to delete this advance salary?')) {
      this.advanceSalaryService.deleteAdvanceSalary(id).subscribe({
        next: () => {
          this.notificationService.showNotify(
            'Advance Salary deleted successfully!',
            'success'
          );
          this.loadAdvanceSalaries(); 
        },
        error: (err) => {
          console.error('Error deleting advance salary:', err);
          this.notificationService.showNotify(
            'Error deleting advance salary!',
            'error'
          );
        },
      });
    }
  }

  editAdvanceSalary(id: number): void {
    this.router.navigate(['/advance-salary/edit', id]);
  }

  viewAdvanceSalary(id: number): void {
    this.router.navigate(['/advance-salary/view', id]);
  }
}
