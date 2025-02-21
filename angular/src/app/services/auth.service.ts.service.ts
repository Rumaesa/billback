import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { User } from '../models/User';

@Injectable({
  providedIn: 'root'
})
export class AuthServiceTsService {

  private headers: HttpHeaders;

  constructor(private http: HttpClient) {
    this.headers = new HttpHeaders({
      'Content-Type': 'application/json'
    });
   }

  loginService(loginDto: {username: string, password: string}): Observable<any> {
    let apiUrl = 'http://localhost:8080/api/auth/signin';
    return this.http.post(apiUrl, loginDto, {responseType: 'text', withCredentials: true});
  }

  registerUser(userDetails: User) {
    const registerDto = {
      username: userDetails.fullName,
      password: userDetails.password,
      email: userDetails.email
    };
    let apiUrl = 'http://localhost:8080/api/auth/signup';
    return this.http.post(apiUrl, registerDto, {headers: this.headers, responseType: 'text'});
  }

  logoutService(): Observable<any> {
    let apiUrl = 'http://localhost:8080/api/auth/signout';
    return this.http.post(apiUrl, {responseType: 'text'});
  }

  getUserByUsername(username: string): Observable<User[]>{
    let apiUrl = 'http://localhost:8080/api/auth/username';
    return this.http.get<User[]>(apiUrl, {withCredentials: true});
  }
}
