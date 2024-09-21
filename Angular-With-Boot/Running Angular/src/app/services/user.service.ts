import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { UserModel } from '../models/user.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  private baseUrl: string = environment.apiUrl + '/users';

  // private baseUrl: string = 'http://localhost:8080/api/users';

  constructor(private httpClient: HttpClient) {}

  createUser(formData: FormData): Observable<string> {
    return this.httpClient
      .post<string>(this.baseUrl, formData)
      .pipe(catchError(this.handleError<string>('createUser')));
  }
  // Create a new user
  // createUser(user: UserModel, profilePhoto: File): Observable<string> {
  //   const formData = new FormData();
  //   formData.append('user', JSON.stringify(user));
  //   formData.append('profilePhoto', profilePhoto);

  //   return this.httpClient
  //     .post<string>(`${this.baseUrl}/create`, formData)
  //     .pipe(catchError(this.handleError<string>('createUser')));
  // }

  // Update an existing user
  updateUser(
    id: number,
    user: UserModel,
    profilePhoto?: File
  ): Observable<string> {
    const formData = new FormData();
    formData.append('user', JSON.stringify(user));
    if (profilePhoto) {
      formData.append('profilePhoto', profilePhoto);
    }

    return this.httpClient
      .put<string>(`${this.baseUrl}/update/${id}`, formData)
      .pipe(catchError(this.handleError<string>('updateUser')));
  }

  // Get all users
  getAllUsers(): Observable<UserModel[]> {
    return this.httpClient
      .get<UserModel[]>(this.baseUrl)
      .pipe(catchError(this.handleError<UserModel[]>('getAllUsers', [])));
  }

  // Get a user by ID
  getUserById(id: number): Observable<UserModel> {
    return this.httpClient
      .get<UserModel>(`${this.baseUrl}/${id}`)
      .pipe(catchError(this.handleError<UserModel>('getUserById')));
  }

  // Delete a user by ID
  deleteUserById(id: number): Observable<string> {
    return this.httpClient
      .delete<string>(`${this.baseUrl}/delete/${id}`)
      .pipe(catchError(this.handleError<string>('deleteUserById')));
  }

  // Get a user by email
  getUserByEmail(email: string): Observable<UserModel> {
    return this.httpClient
      .get<UserModel>(`${this.baseUrl}/email/${email}`)
      .pipe(catchError(this.handleError<UserModel>('getUserByEmail')));
  }

  // Get users with salary greater than or equal to a value
  getUsersWithSalaryGreaterThanOrEqual(
    salary: number
  ): Observable<UserModel[]> {
    return this.httpClient
      .get<UserModel[]>(`${this.baseUrl}/salary/greaterThanOrEqual/${salary}`)
      .pipe(
        catchError(
          this.handleError<UserModel[]>(
            'getUsersWithSalaryGreaterThanOrEqual',
            []
          )
        )
      );
  }

  // Get users with salary less than or equal to a value
  getUsersWithSalaryLessThanOrEqual(salary: number): Observable<UserModel[]> {
    return this.httpClient
      .get<UserModel[]>(`${this.baseUrl}/salary/lessThanOrEqual/${salary}`)
      .pipe(
        catchError(
          this.handleError<UserModel[]>('getUsersWithSalaryLessThanOrEqual', [])
        )
      );
  }

  // Get users by role
  getUsersByRole(role: string): Observable<UserModel[]> {
    return this.httpClient
      .get<UserModel[]>(`${this.baseUrl}/role/${role}`)
      .pipe(catchError(this.handleError<UserModel[]>('getUsersByRole', [])));
  }

  // Search users by part of the full name
  getUsersByFullNamePart(name: string): Observable<UserModel[]> {
    return this.httpClient
      .get<UserModel[]>(`${this.baseUrl}/search/name/${name}`)
      .pipe(
        catchError(this.handleError<UserModel[]>('getUsersByFullNamePart', []))
      );
  }

  // Get users by gender
  getUsersByGender(gender: string): Observable<UserModel[]> {
    return this.httpClient
      .get<UserModel[]>(`${this.baseUrl}/gender/${gender}`)
      .pipe(catchError(this.handleError<UserModel[]>('getUsersByGender', [])));
  }

  // Get users by joined date
  getUsersByJoinedDate(joinedDate: string): Observable<UserModel[]> {
    return this.httpClient
      .get<UserModel[]>(`${this.baseUrl}/joinedDate/${joinedDate}`)
      .pipe(
        catchError(this.handleError<UserModel[]>('getUsersByJoinedDate', []))
      );
  }

  // Handle HTTP errors
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
