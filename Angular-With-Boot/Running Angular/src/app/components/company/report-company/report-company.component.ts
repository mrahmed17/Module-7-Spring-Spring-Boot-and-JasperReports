import { Component, OnInit } from '@angular/core';
import { CompanyService } from '../../../services/company.service';
import { NotificationService } from '../../../services/notification.service';

@Component({
  selector: 'app-report-company',
  templateUrl: './report-company.component.html',
  styleUrls: ['./report-company.component.css'],
})
export class ReportCompanyComponent implements OnInit {
  selectedCompanyId!: number;
  selectedBranchId!: number;
  employeeCountInCompany!: number;
  employeeCountInBranch!: number;
  branches: any[] = [];
  departments: any[] = [];
  employees: any[] = [];

  constructor(
    private companyService: CompanyService,
    private notification: NotificationService
  ) {}

  ngOnInit(): void {
  }

  loadBranches(companyId: number) {
    this.companyService.getBranchesByCompanyId(companyId).subscribe({
      next: (branches) => {
        this.branches = branches;
        console.log('Branches: ', branches);
      },
      error: (error) => {
        console.error('Error fetching branches', error);
        this.notification.showNotify('Error fetching branches', 'error');
      },
    });
  }

  loadDepartments(companyId: number) {
    this.companyService.getDepartmentsByCompanyId(companyId).subscribe({
      next: (departments) => {
        this.departments = departments;
        console.log('Departments: ', departments);
      },
      error: (error) => {
        console.error('Error fetching departments', error);
        this.notification.showNotify('Error fetching departments', 'error');
      },
    });
  }

  loadDepartmentsByBranch(branchId: number) {
    this.companyService.getDepartmentsByBranchId(branchId).subscribe({
      next: (departments) => {
        this.departments = departments;
        console.log('Departments by branch: ', departments);
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

  loadEmployeesByDepartment(departmentId: number) {
    this.companyService.getEmployeesByDepartmentId(departmentId).subscribe({
      next: (employees) => {
        this.employees = employees;
        console.log('Employees in department: ', employees);
      },
      error: (error) => {
        console.error('Error fetching employees in department', error);
        this.notification.showNotify(
          'Error fetching employees in department',
          'error'
        );
      },
    });
  }

  countEmployeesInCompany(companyId: number) {
    this.companyService.countEmployeesByCompanyId(companyId).subscribe({
      next: (count) => {
        this.employeeCountInCompany = count;
        console.log('Employees in company: ', count);
      },
      error: (error) => {
        console.error('Error counting employees in company', error);
        this.notification.showNotify(
          'Error counting employees in company',
          'error'
        );
      },
    });
  }

  countEmployeesInBranch(branchId: number) {
    this.companyService.countEmployeesByBranchId(branchId).subscribe({
      next: (count) => {
        this.employeeCountInBranch = count;
        console.log('Employees in branch: ', count);
      },
      error: (error) => {
        console.error('Error counting employees in branch', error);
        this.notification.showNotify(
          'Error counting employees in branch',
          'error'
        );
      },
    });
  }
}
