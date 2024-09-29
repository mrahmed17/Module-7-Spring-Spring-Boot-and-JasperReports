import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AdvanceSalaryModel } from '../models/advance-salary.model';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root',
})
export class AdvanceSalaryService {
  private baseUrl: string = `${environment.apiUrl}/advance-salary`;

  constructor(private http: HttpClient) {}

  createAdvanceSalary(
    advanceSalary: AdvanceSalaryModel
  ): Observable<AdvanceSalaryModel> {
    return this.http.post<AdvanceSalaryModel>(
      `${this.baseUrl}/create`,
      advanceSalary
    );
  }

  updateAdvanceSalary(
    id: number,
    advanceSalary: AdvanceSalaryModel
  ): Observable<AdvanceSalaryModel> {
    return this.http.put<AdvanceSalaryModel>(
      `${this.baseUrl}/update/${id}`,
      advanceSalary
    );
  }

  deleteAdvanceSalary(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/delete/${id}`);
  }

  getAdvanceSalaryById(id: number): Observable<AdvanceSalaryModel> {
    return this.http.get<AdvanceSalaryModel>(`${this.baseUrl}/find/${id}`);
  }

  getAdvanceSalariesByUser(userId: number): Observable<AdvanceSalaryModel[]> {
    return this.http.get<AdvanceSalaryModel[]>(
      `${this.baseUrl}/user/${userId}`
    );
  }

  getAdvanceSalariesByMonth(month: string): Observable<AdvanceSalaryModel[]> {
    return this.http.get<AdvanceSalaryModel[]>(
      `${this.baseUrl}/month/${month}`
    );
  }

  getTotalAdvanceSalaryByUserId(userId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/user-total/${userId}`);
  }

  getAdvanceSalariesByDateRange(
    startDate: Date,
    endDate: Date
  ): Observable<AdvanceSalaryModel[]> {
    const params = new HttpParams()
      .set('startDate', startDate.toISOString())
      .set('endDate', endDate.toISOString());
    return this.http.get<AdvanceSalaryModel[]>(`${this.baseUrl}/date-range`, {
      params,
    });
  }

  getAdvanceSalariesByYear(year: number): Observable<AdvanceSalaryModel[]> {
    return this.http.get<AdvanceSalaryModel[]>(`${this.baseUrl}/year/${year}`);
  }

  getLatestAdvanceSalaryByUser(userId: number): Observable<AdvanceSalaryModel> {
    return this.http.get<AdvanceSalaryModel>(
      `${this.baseUrl}/latest/${userId}`
    );
  }
}
