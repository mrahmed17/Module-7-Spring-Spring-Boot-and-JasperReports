import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { environment } from '../../environments/environment';
import { BranchModel } from '../models/branch.model';
import { DepartmentModel } from '../models/department.model';
import { UserModel } from '../models/user.model';

@Injectable({
  providedIn: 'root',
})
export class BranchService {
  private baseUrl: string = `${environment.apiUrl}/branches`;
  constructor(private http: HttpClient) {}

  // Create a new branch with an optional photo
  createBranch(
    branch: BranchModel,
    branchPhoto?: File
  ): Observable<BranchModel> {
    const formData = new FormData();
    formData.append(
      'branch',
      new Blob([JSON.stringify(branch)], { type: 'application/json' })
    );
    if (branchPhoto) {
      formData.append('branchPhoto', branchPhoto);
    }

    return this.http
      .post<BranchModel>(`${this.baseUrl}/create`, formData)
      .pipe(catchError(this.handleError<BranchModel>('createBranch')));
  }

  // Update an existing branch with an optional photo
  updateBranch(
    id: number,
    branch: BranchModel,
    branchPhoto?: File
  ): Observable<BranchModel> {
    const formData = new FormData();
    formData.append(
      'branch',
      new Blob([JSON.stringify(branch)], { type: 'application/json' })
    );
    if (branchPhoto) {
      formData.append('branchPhoto', branchPhoto);
    }

    return this.http
      .put<BranchModel>(`${this.baseUrl}/update/${id}`, formData)
      .pipe(catchError(this.handleError<BranchModel>('updateBranch')));
  }

  // Delete a branch by its ID
  deleteBranch(id: number): Observable<void> {
    return this.http
      .delete<void>(`${this.baseUrl}/delete/${id}`)
      .pipe(catchError(this.handleError<void>('deleteBranch')));
  }

  // Get a branch by its ID
  getBranchById(id: number): Observable<BranchModel> {
    return this.http
      .get<BranchModel>(`${this.baseUrl}/find/${id}`)
      .pipe(catchError(this.handleError<BranchModel>('getBranchById')));
  }

  // Get all branches
  getAllBranches(): Observable<BranchModel[]> {
    return this.http
      .get<BranchModel[]>(`${this.baseUrl}/all`)
      .pipe(catchError(this.handleError<BranchModel[]>('getAllBranches', [])));
  }

  // Search for a branch by name
  findByBranchName(branchName: string): Observable<BranchModel> {
    const params = new HttpParams().set('branchName', branchName);
    return this.http
      .get<BranchModel>(`${this.baseUrl}/search`, { params })
      .pipe(catchError(this.handleError<BranchModel>('findByBranchName')));
  }

  // Get departments by branch ID
  getDepartmentsByBranchId(branchId: number): Observable<DepartmentModel[]> {
    return this.http
      .get<DepartmentModel[]>(`${this.baseUrl}/${branchId}/departments`)
      .pipe(
        catchError(
          this.handleError<DepartmentModel[]>('getDepartmentsByBranchId', [])
        )
      );
  }

  // Get employees by branch ID
  getEmployeesByBranchId(branchId: number): Observable<UserModel[]> {
    return this.http
      .get<UserModel[]>(`${this.baseUrl}/${branchId}/employees`)
      .pipe(
        catchError(this.handleError<UserModel[]>('getEmployeesByBranchId', []))
      );
  }

  // Count departments by branch ID
  countDepartmentsByBranchId(branchId: number): Observable<number> {
    return this.http
      .get<number>(`${this.baseUrl}/${branchId}/department-count`)
      .pipe(catchError(this.handleError<number>('countDepartmentsByBranchId')));
  }

  // Count employees by branch ID
  countEmployeesByBranchId(branchId: number): Observable<number> {
    return this.http
      .get<number>(`${this.baseUrl}/${branchId}/employee-count`)
      .pipe(catchError(this.handleError<number>('countEmployeesByBranchId')));
  }

  // Get branches by company ID
  getBranchesByCompanyId(companyId: number): Observable<BranchModel[]> {
    return this.http
      .get<BranchModel[]>(`${this.baseUrl}/company/${companyId}`)
      .pipe(
        catchError(
          this.handleError<BranchModel[]>('getBranchesByCompanyId', [])
        )
      );
  }

  // Error handling method
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}

