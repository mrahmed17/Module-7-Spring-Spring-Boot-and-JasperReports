import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { DepartmentService } from '../../../services/department.service';
import { NotificationService } from '../../../services/notification.service';
import { DepartmentModel } from '../../../models/department.model';

@Component({
  selector: 'app-create-department',
  templateUrl: './create-department.component.html',
  styleUrls: ['./create-department.component.css'],
})
export class CreateDepartmentComponent {
  departmentForm: FormGroup;
  departmentPhoto?: File;

  constructor(
    private formBuilder: FormBuilder,
    private departmentService: DepartmentService,
    private notification: NotificationService,
    private router: Router
  ) {
    this.departmentForm = this.formBuilder.group({
      departmentName: ['', Validators.required],
      numOfEmployees: [0, Validators.required],
      photo: [null],
    });
  }

  onPhotoSelected(event: any): void {
    this.departmentPhoto = event.target.files[0];
  }

  onSubmit(): void {
    const department: DepartmentModel = {
      id: 0,
      departmentName: this.departmentForm.value.departmentName,
      numOfEmployees: this.departmentForm.value.numOfEmployees,
      photo: '',
      branch: [],
    };

    this.departmentService
      .createDepartment(department, this.departmentPhoto)
      .subscribe({
        next: () => {
          this.notification.showNotify(
            'Department created successfully',
            'success'
          );
          this.router.navigate(['/department/list']);
        },
        error: (error) => {
          console.error('Error creating department', error);
          this.notification.showNotify('Error creating department', 'error');
        },
      });
  }
}
