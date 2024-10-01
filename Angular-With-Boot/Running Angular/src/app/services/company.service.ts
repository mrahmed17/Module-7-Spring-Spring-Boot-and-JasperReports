import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { CompanyModel } from '../models/company.model';

@Injectable({
  providedIn: 'root',
})
export class CompanyService {
  private baseUrl: string = `${environment.apiUrl}companies`;

  constructor(private http: HttpClient) {}

  getAllCompanies(): Observable<CompanyModel[]> {
    return this.http.get<CompanyModel[]>(`${this.baseUrl}/all`);
  }

  getCompanyById(id: number): Observable<CompanyModel> {
    return this.http.get<CompanyModel>(`${this.baseUrl}/find/${id}`);
  }

  createCompany(company: CompanyModel, companyPhoto?: File): Observable<any> {
    const formData = new FormData();
    formData.append(
      'company',
      new Blob([JSON.stringify(company)], { type: 'application/json' })
    );

    if (companyPhoto) {
      formData.append('companyPhoto', companyPhoto);
    }

    return this.http.post(`${this.baseUrl}/create`, formData, {
      headers: new HttpHeaders({}),
    });
  }

  updateCompany(
    id: number,
    company: CompanyModel,
    companyPhoto?: File
  ): Observable<any> {
    const formData = new FormData();
    formData.append(
      'company',
      new Blob([JSON.stringify(company)], { type: 'application/json' })
    );

    if (companyPhoto) {
      formData.append('companyPhoto', companyPhoto);
    }

    return this.http.put(`${this.baseUrl}/update/${id}`, formData, {
      headers: new HttpHeaders({}),
    });
  }

  deleteCompany(id: number): Observable<any> {
    return this.http.delete(`${this.baseUrl}/delete/${id}`);
  }

  findByCompanyName(companyName: string): Observable<CompanyModel> {
    return this.http.get<CompanyModel>(
      `${this.baseUrl}/search?companyName=${companyName}`
    );
  }

  getBranchesByCompanyId(companyId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${companyId}/branches`);
  }

  getDepartmentsByBranchId(branchId: number): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.baseUrl}/branches/${branchId}/departments`
    );
  }

  getDepartmentsByCompanyId(companyId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/${companyId}/departments`);
  }

  getEmployeesByDepartmentId(departmentId: number): Observable<any[]> {
    return this.http.get<any[]>(
      `${this.baseUrl}/departments/${departmentId}/employees`
    );
  }

  countEmployeesByCompanyId(companyId: number): Observable<number> {
    return this.http.get<number>(`${this.baseUrl}/${companyId}/employee-count`);
  }

  countEmployeesByBranchId(branchId: number): Observable<number> {
    return this.http.get<number>(
      `${this.baseUrl}/branches/${branchId}/employee-count`
    );
  }
}
