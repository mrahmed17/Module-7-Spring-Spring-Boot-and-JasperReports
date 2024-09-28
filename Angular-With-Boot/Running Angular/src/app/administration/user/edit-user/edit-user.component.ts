import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { NotificationService } from '../../../services/notification.service';
import { UserModel } from '../../../models/user.model';
import {
  faCalendarAlt,
  faCalendarDay,
  faDollarSign,
  faEnvelope,
  faHome,
  faIdCard,
  faImage,
  faKey,
  faPhone,
  faUser,
  faUserEdit,
  faUserTag,
  faVenusMars,
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
  faUserEdit = faUserEdit;

  editUserForm!: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';
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
    this.editUserForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      address: [''],
      gender: [''],
      dateOfBirth: ['', Validators.required],
      nationalId: ['', Validators.required],
      contact: ['', Validators.required],
      basicSalary: ['', Validators.required],
      joinedDate: ['', Validators.required],
      role: ['', Validators.required],
      profilePhoto: [null],
    });

    // Extract userId from route params and load user details
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.userId = +id;
      this.loadUser(this.userId);
    }
  }

  loadUser(id: number): void {
    this.userService.getUserById(id).subscribe({
      next: (user) => {
        // Convert the date fields to strings (YYYY-MM-DD) for the form
        const userFormData = {
          ...user,
          dateOfBirth: user.dateOfBirth
            ? new Date(user.dateOfBirth).toISOString().split('T')[0]
            : '',
          joinedDate: user.joinedDate
            ? new Date(user.joinedDate).toISOString().split('T')[0]
            : '',
        };

        // Patch form value
        this.editUserForm.patchValue(userFormData as any); // Cast to 'any' to avoid type error
      },
      error: (err) => {
        this.errorMessage = 'Error loading user details';
        console.error(err);
      },
    });
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
      }
    }
  }

  onSubmit(): void {
    if (this.editUserForm.valid) {
      const formValues = this.editUserForm.value;

      // Convert string dates from the form back to Date objects
      const user: UserModel = {
        ...formValues,
        dateOfBirth: formValues.dateOfBirth
          ? new Date(formValues.dateOfBirth)
          : undefined,
        joinedDate: formValues.joinedDate
          ? new Date(formValues.joinedDate)
          : undefined,
      };

      const fileToSend: File | undefined = this.selectedFile || undefined;

      this.userService.updateUser(this.userId, user, fileToSend).subscribe({
        next: () => {
          this.successMessage = 'User updated successfully!';
          this.errorMessage = '';
          this.notificationService.showNotify(
            'User updated successfully!',
            'success'
          );
          this.router.navigate(['/user/list']);
        },
        error: (error) => {
          this.errorMessage = 'Failed to update user';
          this.successMessage = '';
          console.error(error);
        },
      });
    } else {
      this.errorMessage = 'Please fill in all required fields correctly.';
      this.successMessage = '';
    }
  }

  cancel(): void {
    this.router.navigate(['/user/list']);
  }
}
