import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, tap } from 'rxjs';
import { Constant } from '../../const/Constants';
import { StorageService } from '../storage/storage.service';

export const AUTH_HEADER = 'authorization';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient, private storage: StorageService) { }

  login(email: string, password: string): Observable<any>{
    return this.http.post(Constant.BASIC_URL + "authenticate", {
      email,
      password
    },{observe: 'response'})
    .pipe(
      tap(__ => this.log("User Authentication")),
      map((res: HttpResponse<any>) =>{
        this.storage.saveUser(res.body);
        const tokenLength = res.headers.get(AUTH_HEADER).length;
        const bearerToken = res.headers.get(AUTH_HEADER).substring(7, tokenLength);
        this.storage.saveToken(bearerToken);
        return res;
      })
    )
  }

  log(message: string){
    console.log(message);
  }
}
