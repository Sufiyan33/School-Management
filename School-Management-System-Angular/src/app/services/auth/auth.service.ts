import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constant } from '../../const/Constants';

//const BASIC_URL = ['http://localhost:8080/'];
@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(loginRequest: any): Observable<any>{
    return this.http.post(Constant.BASIC_URL + "authenticate", loginRequest);
  }
}
