import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';
import { UserModel } from '../../../models/user.model';
import { Router } from '@angular/router';
import {
  faArrowLeft,
  faCalendarAlt,
  faCalendarDay,
  faDollarSign,
  faEnvelope,
  faHome,
  faIdCard,
  faImage,
  faKey,
  faPhone,
  faPlusCircle,
  faUser,
  faUserPlus,
  faUserTag,
  faVenusMars,
} from '@fortawesome/free-solid-svg-icons';
import { RoleEnum } from '../../../models/role.enum';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css'],
})
export class CreateUserComponent {
  faUserPlus = faUserPlus;
  faHome = faHome;
  faVenusMars = faVenusMars;
  faCalendarAlt = faCalendarAlt;
  faUser = faUser;
  faIdCard = faIdCard;
  faCalendarDay = faCalendarDay;
  faDollarSign = faDollarSign;
  faEnvelope = faEnvelope;
  faPhone = faPhone;
  faUserTag = faUserTag;
  faArrowLeft = faArrowLeft;
  faImage = faImage;
  faPlusCircle = faPlusCircle;
  faKey = faKey;

  userForm: FormGroup;
  profilePhoto: File | null = null;
  profilePhotoPreview: string | null = null;
  successMessage: string | null = null;
  errorMessage: string | null = null;
  currentStep = 1;

  genderOptions = ['Male', 'Female', 'Other'];
  roleOptions = Object.values(RoleEnum).filter((values) =>
    isNaN(Number(values))
  );

  constructor(
    private fb: FormBuilder,
    private userService: UserService,
    private router: Router
  ) {
    this.userForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      address: [''],
      gender: ['', Validators.required],
      dateOfBirth: [''],
      nationalId: [''],
      contact: [''],
      basicSalary: [''],
      joinedDate: [''],
      role: ['', Validators.required],
    });
  }

  onFileChange(event: any) {
    const file = event.target.files[0];
    if (file) {
      const maxSize = 5 * 1024 * 1024; // 5MB
      const allowedTypes = ['image/jpeg', 'image/png'];

      if (allowedTypes.includes(file.type) && file.size <= maxSize) {
        this.profilePhoto = file;
        const reader = new FileReader();
        reader.onload = () => {
          this.profilePhotoPreview = reader.result as string;
        };
        reader.readAsDataURL(file);
      } else {
        this.errorMessage =
          'Invalid file type or size. Please upload a .jpg or .png image below 5MB.';
      }
    }
  }

  nextStep() {
    if (this.currentStep < 3) {
      this.currentStep++;
    }
  }

  previousStep() {
    if (this.currentStep > 1) {
      this.currentStep--;
    }
  }

  onSubmit() {
    if (this.userForm.invalid) {
      this.errorMessage = 'Please fill out the form correctly.';
      return;
    }
    const user: UserModel = {
      id: 0, 
      fullName: this.userForm.get('fullName')?.value,
      email: this.userForm.get('email')?.value,
      password: this.userForm.get('password')?.value,
      address: this.userForm.get('address')?.value,
      gender: this.userForm.get('gender')?.value,
      dateOfBirth: this.userForm.get('dateOfBirth')?.value,
      nationalid: this.userForm.get('nationalId')?.value,
      contact: this.userForm.get('contact')?.value,
      basicSalary: this.userForm.get('basicSalary')?.value,
      joinedDate: this.userForm.get('joinedDate')?.value,
      role: this.userForm.get('role')?.value,
      isActive: this.userForm.get('isActive')?.value,
      profilePhoto: this.userForm.get('profilePhoto')?.value,
      updatedAt: this.userForm.get('updatedAt')?.value,
      department: this.userForm.get('depdegree')?.value,
    };
    const formData = new FormData();
    formData.append(
      'user',
      new Blob([JSON.stringify(user)], { type: 'application/json' })
    );

    if (this.profilePhoto) {
      formData.append('profilePhoto', this.profilePhoto);
    }
    this.userService.createUser(formData).subscribe({
      next: (message) => {
        this.successMessage = message;
        this.errorMessage = null;
        this.userForm.reset();
        this.profilePhoto = null;
        this.router.navigate(['/user/list']);
      },
      error: (err) => {
        this.successMessage = null;
        this.errorMessage =
          err?.error?.message || 'An error occurred while creating the user.';
        console.error(err);
      },
    });
  }
}
