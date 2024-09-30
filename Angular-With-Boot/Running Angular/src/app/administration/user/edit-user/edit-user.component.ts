import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { NotificationService } from '../../../services/notification.service';
import { UserModel } from '../../../models/user.model';
import {
  faUser,
  faEnvelope,
  faKey,
  faHome,
  faVenusMars,
  faCalendarAlt,
  faIdCard,
  faPhone,
  faDollarSign,
  faCalendarDay,
  faUserTag,
  faImage,
} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-edit-user',
  templateUrl: './edit-user.component.html',
  styleUrls: ['./edit-user.component.css'],
})
export class EditUserComponent implements OnInit {
  faUser = faUser;
  faEnvelope = faEnvelope;
  faKey = faKey;
  faHome = faHome;
  faVenusMars = faVenusMars;
  faCalendarAlt = faCalendarAlt;
  faIdCard = faIdCard;
  faPhone = faPhone;
  faDollarSign = faDollarSign;
  faCalendarDay = faCalendarDay;
  faUserTag = faUserTag;
  faImage = faImage;

  editUserForm!: FormGroup;
  errorMessage: string | null = null;
  successMessage: string | null = null;
  selectedFile: File | null = null;
  userId!: number;

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private route: ActivatedRoute,
    private router: Router,
    private notificationService: NotificationService
  ) {}

  ngOnInit(): void {
    this.initializeForm();
    this.loadUser();
  }

  initializeForm() {
    this.editUserForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      address: [''],
      gender: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      nationalId: ['', Validators.required],
      contact: ['', Validators.required],
      basicSalary: ['', Validators.required],
      joinedDate: ['', Validators.required],
      role: ['', Validators.required],
      profilePhoto: [null],
    });
  }

  loadUser(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.userId = +id;
      this.userService.getUserById(this.userId).subscribe({
        next: (user) => {
          const userFormData = {
            ...user,
            dateOfBirth: user.dateOfBirth
              ? new Date(user.dateOfBirth).toISOString().split('T')[0]
              : '',
            joinedDate: user.joinedDate
              ? new Date(user.joinedDate).toISOString().split('T')[0]
              : '',
          };
          this.editUserForm.patchValue(userFormData as any);
        },
        error: (err) => {
          this.errorMessage = 'Error loading user details';
          console.error(err);
        },
      });
    }
  }

  onFileChange(event: any): void {
    const file = event.target.files[0];
    if (file) {
      const maxSize = 5 * 1024 * 1024; // 5MB limit
      const allowedTypes = ['image/jpeg', 'image/png'];

      if (file.size > maxSize) {
        this.errorMessage = 'File size should be less than 5MB.';
        this.selectedFile = null;
      } else if (!allowedTypes.includes(file.type)) {
        this.errorMessage =
          'Invalid file type. Please upload a JPG or PNG file.';
        this.selectedFile = null;
      } else {
        this.selectedFile = file;
        this.editUserForm.patchValue({ profilePhoto: this.selectedFile });
        this.errorMessage = null;
      }
    }
  }

  onSubmit(): void {
    if (this.editUserForm.invalid) {
      this.errorMessage = 'Please fill in all required fields correctly.';
      return;
    }

    const formValues = this.editUserForm.value;
    const user: UserModel = {
      ...formValues,
      dateOfBirth: formValues.dateOfBirth
        ? new Date(formValues.dateOfBirth)
        : undefined,
      joinedDate: formValues.joinedDate
        ? new Date(formValues.joinedDate)
        : undefined,
    };

    const formData = new FormData();
    formData.append(
      'user',
      new Blob([JSON.stringify(user)], { type: 'application/json' })
    );

    if (this.selectedFile) {
      formData.append('profilePhoto', this.selectedFile);
    }

    this.userService.updateUser(this.userId, formData).subscribe({
      next: () => {
        this.successMessage = 'User updated successfully!';
        this.errorMessage = null;
        this.notificationService.showNotify(
          'User updated successfully!',
          'success'
        );
        this.router.navigate(['/user/list']);
      },
      error: (err) => {
        this.errorMessage = 'Failed to update user';
        this.successMessage = null;
        console.error(err);
      },
    });
  }

  cancel(): void {
    this.router.navigate(['/user/list']);
  }
}
