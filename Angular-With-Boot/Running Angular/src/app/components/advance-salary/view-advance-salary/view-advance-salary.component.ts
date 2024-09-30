import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AdvanceSalaryModel } from '../../../models/advance-salary.model';
import { AdvanceSalaryService } from '../../../services/advancesalary.service';
import { NotificationService } from '../../../services/notification.service';

@Component({
  selector: 'app-view-advance-salary',
  templateUrl: './view-advance-salary.component.html',
  styleUrls: ['./view-advance-salary.component.css'],
})
export class ViewAdvanceSalaryComponent implements OnInit {
  advanceSalary: AdvanceSalaryModel = new AdvanceSalaryModel();
  advanceSalaryId!: number;

  constructor(
    private route: ActivatedRoute,
    private advanceSalaryService: AdvanceSalaryService,
    private router: Router,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.advanceSalaryId = +this.route.snapshot.paramMap.get('id')!;
    this.advanceSalaryService
      .getAdvanceSalaryById(this.advanceSalaryId)
      .subscribe({
        next: (response: AdvanceSalaryModel) => {
          this.advanceSalary = response;
        },
        error: (err) => {
          console.error('Error fetching advance salary:', err);
          this.notificationService.showNotify(
            'Error fetching advance salary!',
            'error'
          );
        },
      });
  }

  backToList(): void {
    this.router.navigate(['/advance-salary/list']);
  }
}
