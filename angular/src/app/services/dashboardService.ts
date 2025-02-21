import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { DistributorFieldMapping } from '../models/DistributorFieldMapping';

@Injectable({
  providedIn: 'root'
})
export class DashboardService {

  private readonly headers: HttpHeaders;

  constructor(private http: HttpClient){
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
  }

  getActiveDistributors(): Observable<any> {
    let apiUrl = '/api/getActiveDistributors';
    return this.http.get<any>(apiUrl, {withCredentials: true});
  }

  getDistributorMapping(mappingId: number): Observable<any> {
    let apiUrl = `/api/getDistributorMapping/${mappingId}`;
    return this.http.get<any>(apiUrl, {withCredentials: true});
  }

  createDistributorMapping(distributorMapping: DistributorFieldMapping, distributorId: number, create: boolean): Observable<any> {
    let apiUrl = `/api/createDistributorMapping/${distributorId}/${create}`;
    return this.http.post<any>(apiUrl,distributorMapping, {withCredentials: true});
  }

  
 }