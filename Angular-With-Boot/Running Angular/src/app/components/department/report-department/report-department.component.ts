import { Component, OnInit } from '@angular/core';
import { DepartmentService } from '../../../services/department.service';
import { DepartmentModel } from '../../../models/department.model';
import { UserModel } from '../../../models/user.model';
import { NotificationService } from '../../../services/notification.service';

@Component({
  selector: 'app-report-department',
  templateUrl: './report-department.component.html',
  styleUrls: ['./report-department.component.css'],
})
export class ReportDepartmentComponent implements OnInit {
  departments: DepartmentModel[] = [];
  employeesCount: number = 0;

  constructor(
    private departmentService: DepartmentService,
    private notification: NotificationService
  ) {}

  ngOnInit(): void {
    this.loadDepartments();
  }

  loadDepartments(): void {
    this.departmentService.getDepartmentsByCompanyId(1).subscribe({
      next: (data) => {
        this.departments = data;
      },
      error: (error) => {
        console.error('Error fetching departments', error);
        this.notification.showNotify('Error fetching departments', 'error');
      },
    });
  }

  getEmployeesByDepartmentId(departmentId: number): void {
    this.departmentService.getEmployeesByDepartmentId(departmentId).subscribe({
      next: (employees: UserModel[]) => {
        console.log('Employees:', employees);
      },
      error: (error) => {
        console.error('Error fetching employees', error);
        this.notification.showNotify('Error fetching employees', 'error');
      },
    });
  }

  countEmployeesByDepartmentId(departmentId: number): void {
    this.departmentService
      .countEmployeesByDepartmentId(departmentId)
      .subscribe({
        next: (count) => {
          this.employeesCount = count;
        },
        error: (error) => {
          console.error('Error counting employees', error);
          this.notification.showNotify('Error counting employees', 'error');
        },
      });
  }

  getDepartmentsByBranchId(branchId: number): void {
    this.departmentService.getDepartmentsByBranchId(branchId).subscribe({
      next: (data) => {
        this.departments = data;
      },
      error: (error) => {
        console.error('Error fetching departments by branch', error);
        this.notification.showNotify(
          'Error fetching departments by branch',
          'error'
        );
      },
    });
  }

  getDepartmentsByCompanyId(companyId: number): void {
    this.departmentService.getDepartmentsByCompanyId(companyId).subscribe({
      next: (data) => {
        this.departments = data;
      },
      error: (error) => {
        console.error('Error fetching departments by company', error);
        this.notification.showNotify(
          'Error fetching departments by company',
          'error'
        );
      },
    });
  }
}
