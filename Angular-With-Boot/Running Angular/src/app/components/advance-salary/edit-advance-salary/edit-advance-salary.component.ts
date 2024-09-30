import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdvanceSalaryModel } from '../../../models/advance-salary.model';
import { AdvanceSalaryService } from '../../../services/advancesalary.service';
import { MonthEnum } from '../../../models/month.enum';
import { NotificationService } from '../../../services/notification.service';
import { UserModel } from '../../../models/user.model';
import { UserService } from '../../../services/user.service';


@Component({
  selector: 'app-edit-advance-salary',
  templateUrl: './edit-advance-salary.component.html',
  styleUrls: ['./edit-advance-salary.component.css'],
})
export class EditAdvanceSalaryComponent implements OnInit {
  advanceSalary: AdvanceSalaryModel = new AdvanceSalaryModel();
  months: string[] = [];
  users: UserModel[] = [];
  id!: number;

  constructor(
    private advanceSalaryService: AdvanceSalaryService,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.id = this.route.snapshot.params['id'];

    this.months = Object.keys(MonthEnum).filter((key) => isNaN(Number(key)));

    this.advanceSalaryService.getAdvanceSalaryById(this.id).subscribe({
      next: (data) => {
        this.advanceSalary = data;
      },
      error: (err) => {
        console.error('Error fetching advance salary:', err);
        this.notificationService.showNotify(
          'Error fetching advance salary details!',
          'error'
        );
      },
    });

    this.userService.getAllUsers().subscribe((data) => {
      this.users = data;
    });
  }

  updateAdvanceSalary(): void {
    this.advanceSalaryService
      .updateAdvanceSalary(this.id, this.advanceSalary)
      .subscribe({
        next: (response) => {
          this.notificationService.showNotify(
            'Advance Salary updated successfully!',
            'success'
          );
          this.router.navigate(['/advance-salary/list']);
          console.log('Advance salary updated successfully:', response);
        },
        error: (err) => {
          console.error('Error updating advance salary:', err);
          this.notificationService.showNotify(
            'Error updating advance salary!',
            'error'
          );
        },
      });
  }

  cancel(): void {
    this.router.navigate(['/advance-salary/list']);
  }
}