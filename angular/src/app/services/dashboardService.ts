import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

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
    let apiUrl = 'http://localhost:8080/getActiveDistributors';
    return this.http.get<any>(apiUrl);
  }
  
 }