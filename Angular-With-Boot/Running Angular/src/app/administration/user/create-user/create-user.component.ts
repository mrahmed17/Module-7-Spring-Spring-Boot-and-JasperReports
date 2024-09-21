import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { UserService } from '../../../services/user.service';

// import {
//   faArrowLeft,
//   faCalendarAlt,
//   faCalendarDay,
//   faDollarSign,
//   faEnvelope,
//   faHome,
//   faIdCard,
//   faImage,
//   faPhone,
//   faPlusCircle,
//   faUser,
//   faUserPlus,
//   faUserTag,
//   faVenusMars,
// } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-create-user',
  templateUrl: './create-user.component.html',
  styleUrls: ['./create-user.component.css'],
})
export class CreateUserComponent {
  // faUserPlus = faUserPlus;
  // faHome = faHome;
  // faVenusMars = faVenusMars;
  // faCalendarAlt = faCalendarAlt;
  // faUser = faUser;
  // faIdCard = faIdCard;
  // faCalendarDay = faCalendarDay;
  // faDollarSign = faDollarSign;
  // faEnvelope = faEnvelope;
  // faPhone = faPhone;
  // faUserTag = faUserTag;
  // faArrowLeft = faArrowLeft;
  // faImage = faImage;
  // faPlusCircle = faPlusCircle;

  userForm: FormGroup;
  profilePhoto: File | null = null;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(private fb: FormBuilder, private userService: UserService) {
    this.userForm = this.fb.group({
      fullName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      address: [''],
      gender: [''],
      dateOfBirth: [''],
      nationalId: [''],
      contact: [''],
      basicSalary: [''],
      joinedDate: [''],
      role: [''],
    });
  }

  onFileChange(event: any) {
  const file = event.target.files[0];
  if (file) {
    const maxSize = 5 * 1024 * 1024; // 5MB
    const allowedTypes = ['image/jpeg', 'image/png'];

    if (allowedTypes.includes(file.type) && file.size <= maxSize) {
      this.profilePhoto = file;
    } else {
      this.errorMessage = 'Invalid file type or size. Please upload a .jpg or .png image below 5MB.';
    }
  }
}

  onSubmit() {
  if (this.userForm.invalid) {
    this.errorMessage = 'Please fill out the form correctly.';
    return;
  }

  const formData = new FormData();
  
  // Append form controls individually to the formData object
  Object.keys(this.userForm.controls).forEach(key => {
    formData.append(key, this.userForm.get(key)?.value);
  });

  // Append the profile photo if selected
  if (this.profilePhoto) {
    formData.append('profilePhoto', this.profilePhoto);
  }

  this.userService.createUser(formData).subscribe({
    next: (message) => {
      this.successMessage = message;
      this.errorMessage = null;
      this.userForm.reset();
      this.profilePhoto = null;
    },
    error: (err) => {
      // Use specific error message if available
      this.successMessage = null;
      this.errorMessage = err?.error?.message || 'An error occurred while creating the user.';
      console.error(err);
    }
  });
}

}
