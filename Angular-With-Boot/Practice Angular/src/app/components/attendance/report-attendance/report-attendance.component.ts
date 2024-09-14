import { Component } from '@angular/core';
import { UserModel } from '../../../models/user.model';
import { AttendanceService } from '../../../services/attendance.service';
import { AttendanceModel } from '../../../models/attendance.model';

@Component({
  selector: 'app-report-attendance',
  templateUrl: './report-attendance.component.html',
  styleUrl: './report-attendance.component.css',
})
export class ReportAttendanceComponent {
  attendance: AttendanceModel | null = null;
  attendances: AttendanceModel[] = [];
  userAttendances: Map<UserModel, number> | null = null;
  errorMessage: string | null = null;

  constructor(private attendanceService: AttendanceService) {}

  getAttendanceById(id: number): void {
    this.attendanceService.getAttendanceById(id).subscribe({
      next: (data) => (this.attendance = data),
      error: (error) => (this.errorMessage = `Error: ${error.message}`),
    });
  }

  getAttendancesByUserId(id: number): void {
    this.attendanceService.getAttendancesByUserId(id).subscribe({
      next: (data) => (this.attendances = data),
      error: (error) => (this.errorMessage = `Error: ${error.message}`),
    });
  }

  getUsersWithAttendanceInRange(startDate: string, endDate: string): void {
    this.attendanceService
      .getAttendanceInRange(startDate, endDate)
      .subscribe({
        next: (data) => (this.userAttendances = data),
        error: (error) => (this.errorMessage = `Error: ${error.message}`),
      });
  }

  // startDate!: string;
  // endDate!: string;
  // userAttendance: Map<UserModel, number> = new Map();

  // constructor(private attendanceService: AttendanceService) {}

  // getAttendanceInRange() {
  //   this.attendanceService
  //     .getAttendanceInRange(this.startDate, this.endDate)
  //     .subscribe({
  //       next: data => {
  //         this.userAttendance = data;
  //       },
  //       error: error => {
  //         console.error('Error fetching attendance in range', error);
  //       }
  //     });
  // }
}