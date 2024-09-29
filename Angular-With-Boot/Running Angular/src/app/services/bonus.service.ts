import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { environment } from '../../environments/environment';
import { BonusModel } from '../models/bonus.model';
import { MonthEnum } from '../models/month.enum';

@Injectable({
  providedIn: 'root',
})
export class BonusService {
  private baseUrl: string =`${environment.apiUrl}/bonuses`;

  constructor(private http: HttpClient) {}

  // Create a new bonus
  createBonus(bonus: BonusModel): Observable<BonusModel> {
    return this.http
      .post<BonusModel>(`${this.baseUrl}/create`, bonus)
      .pipe(catchError(this.handleError<BonusModel>('createBonus')));
  }

  // Update an existing bonus
  updateBonus(id: number, bonus: BonusModel): Observable<BonusModel> {
    return this.http
      .put<BonusModel>(`${this.baseUrl}/update/${id}`, bonus)
      .pipe(catchError(this.handleError<BonusModel>('updateBonus')));
  }

  // Get all bonuses
  getAllBonuses(): Observable<BonusModel[]> {
    return this.http
      .get<BonusModel[]>(`${this.baseUrl}/all`)
      .pipe(catchError(this.handleError<BonusModel[]>('getAllBonuses', [])));
  }

  // Calculate the bonus for a user in a specific year
  calculateBonus(userId: number, year: number): Observable<number> {
    return this.http
      .get<number>(`${this.baseUrl}/calculate/${userId}/${year}`)
      .pipe(catchError(this.handleError<number>('calculateBonus')));
  }

  // Get total bonus for a user in a specific year
  getTotalBonusForUser(userId: number, year: number): Observable<number> {
    return this.http
      .get<number>(`${this.baseUrl}/byUserId/${userId}`)
      .pipe(catchError(this.handleError<number>('getTotalBonusForUser')));
  }

  // Get all bonuses for a specific month and year
  getBonusesByMonthAndYear(
    month: MonthEnum,
    year: number
  ): Observable<BonusModel[]> {
    return this.http
      .get<BonusModel[]>(`${this.baseUrl}/month/${month}/year/${year}`)
      .pipe(
        catchError(
          this.handleError<BonusModel[]>('getBonusesByMonthAndYear', [])
        )
      );
  }

  // Get the total bonus paid in a specific year
  getTotalBonusPaidInYear(year: number): Observable<number> {
    return this.http
      .get<number>(`${this.baseUrl}/byYear/${year}`)
      .pipe(catchError(this.handleError<number>('getTotalBonusPaidInYear')));
  }

  // Get all bonuses between two dates
  getBonusesBetweenDates(
    startDate: Date,
    endDate: Date
  ): Observable<BonusModel[]> {
    const params = new HttpParams()
      .set('startDate', startDate.toISOString())
      .set('endDate', endDate.toISOString());
    return this.http
      .get<BonusModel[]>(`${this.baseUrl}/dateRange`, { params })
      .pipe(
        catchError(this.handleError<BonusModel[]>('getBonusesBetweenDates', []))
      );
  }

  // Error handling function
  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }
}
