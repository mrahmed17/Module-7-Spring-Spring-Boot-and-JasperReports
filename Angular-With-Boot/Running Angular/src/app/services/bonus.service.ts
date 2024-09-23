import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { BonusModel } from '../models/bonus.model';
import { MonthEnum } from '../models/month.enum';

@Injectable({
  providedIn: 'root',
})
export class BonusService {
  private baseUrl: string = environment.apiUrl + '/bonuses';

  constructor(private http: HttpClient) {}

  // Create a new bonus
  createBonus(bonus: BonusModel): Observable<BonusModel> {
    return this.http.post<BonusModel>(`${this.baseUrl}/create`, bonus);
  }

  // Update an existing bonus
  updateBonus(id: number, bonus: BonusModel): Observable<BonusModel> {
    return this.http.put<BonusModel>(`${this.baseUrl}/update/${id}`, bonus);
  }

  // Calculate the bonus for a user in a specific year
  calculateBonus(userId: number, year: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/calculate/${userId}/${year}`);
  }

  // Get total bonus for a user in a specific year
  getTotalBonusForUser(userId: number, year: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/total/${userId}/${year}`);
  }

  // Get all bonuses for a specific month and year
  getBonusesByMonthAndYear(
    month: MonthEnum,
    year: number
  ): Observable<BonusModel[]> {
    return this.http.get<BonusModel[]>(
      `${this.baseUrl}/month/${month}/${year}`
    );
  }

  // Get the total bonus paid in a specific year
  getTotalBonusPaidInYear(year: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/total/year/${year}`);
  }

  // Get bonus for a user for a specific month and year
  getBonusForUserByMonthAndYear(
    userId: number,
    month: MonthEnum,
    year: number
  ): Observable<BonusModel> {
    return this.http.get<BonusModel>(
      `${this.baseUrl}/user/${userId}/month/${month}/${year}`
    );
  }

  // Get all bonuses between two dates
  getBonusesBetweenDates(
    startDate: Date,
    endDate: Date
  ): Observable<BonusModel[]> {
    const params = new HttpParams()
      .set('startDate', startDate.toISOString())
      .set('endDate', endDate.toISOString());
    return this.http.get<BonusModel[]>(`${this.baseUrl}/between`, { params });
  }

  // Get users who received a bonus in a specific year
  getUsersWhoReceivedBonusInYear(year: number): Observable<number[]> {
    return this.http.get<number[]>(`${this.baseUrl}/users/year/${year}`);
  }

  // Get the latest bonus for a specific user
  getLatestBonusForUser(userId: number): Observable<BonusModel> {
    return this.http.get<BonusModel>(`${this.baseUrl}/latest/${userId}`);
  }

  // Count the number of bonuses for a user in a specific year
  countBonusesForUserInYear(userId: number, year: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/count/${userId}/${year}`);
  }

  // Get total bonus for a specific month and year
  getTotalBonusForMonthAndYear(
    month: MonthEnum,
    year: number
  ): Observable<number> {
    return this.http.get<number>(
      `${this.baseUrl}/total/month/${month}/${year}`
    );
  }
}
