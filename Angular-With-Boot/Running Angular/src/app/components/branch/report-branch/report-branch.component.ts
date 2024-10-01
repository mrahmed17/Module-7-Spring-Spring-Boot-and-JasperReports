import { Component, OnInit } from '@angular/core';
import { BranchService } from '../../../services/branch.service';
import { NotificationService } from '../../../services/notification.service';
import { BranchModel } from '../../../models/branch.model';

@Component({
  selector: 'app-report-branch',
  templateUrl: './report-branch.component.html',
  styleUrls: ['./report-branch.component.css'],
})
export class ReportBranchComponent implements OnInit {
  branches: BranchModel[] = [];
  totalBranches: number = 0;
  totalDepartments: number = 0;
  totalEmployees: number = 0;
  companyId: number = 0;

  constructor(
    private branchService: BranchService,
    private notification: NotificationService
  ) {}

  ngOnInit(): void {
    this.loadBranches();
    this.countTotalBranches();
    this.countDepartmentsByCompanyId(this.companyId);
    this.countEmployeesByCompanyId(this.companyId);
  }

  loadBranches() {
    this.branchService.getAllBranches().subscribe({
      next: (data) => {
        this.branches = data;
      },
      error: (error) => {
        console.error('Error fetching branches', error);
        this.notification.showNotify('Error fetching branches', 'error');
      },
    });
  }

  countTotalBranches() {
    this.branchService.getAllBranches().subscribe((data) => {
      this.totalBranches = data.length;
    });
  }

  countDepartmentsByCompanyId(companyId: number) {
    this.branchService
      .getBranchesByCompanyId(companyId)
      .subscribe((branches) => {
        let departmentCount = 0;
        branches.forEach((branch) => {
          this.branchService
            .countDepartmentsByBranchId(branch.id)
            .subscribe((count) => {
              departmentCount += count;
            });
        });
        this.totalDepartments = departmentCount;
      });
  }

  countEmployeesByCompanyId(companyId: number) {
    this.branchService
      .getBranchesByCompanyId(companyId)
      .subscribe((branches) => {
        let employeeCount = 0;
        branches.forEach((branch) => {
          this.branchService
            .countEmployeesByBranchId(branch.id)
            .subscribe((count) => {
              employeeCount += count;
            });
        });
        this.totalEmployees = employeeCount;
      });
  }
}
