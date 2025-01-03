import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Constant } from '../../../const/Constants';
import { StorageService } from '../../../services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  getStudentById(): Observable<any>{
      return this.http.get<[]>(Constant.BASIC_URL + `api/student/${StorageService.getUserId()}`, {
        headers: this.createAuthorizationHeader()
    }) 
  }

  applyLeave(studentLeaveDto: any): Observable<any>{
    studentLeaveDto.userId = StorageService.getUserId();
    return this.http.post<[]>(Constant.BASIC_URL + `api/student/leave`, studentLeaveDto, {
      headers: this.createAuthorizationHeader()
    }) 
  }

  getAllAppliedLeaveByStudent(): Observable<any>{
    return this.http.get<[]>(Constant.BASIC_URL + `api/student/leave/${StorageService.getUserId()}`, {
      headers: this.createAuthorizationHeader()
    }) 
  }

  createAuthorizationHeader(): HttpHeaders{
      let authHeaders: HttpHeaders = new HttpHeaders();
      let token = StorageService.getToken();
      if(!token){
        console.error('No token found!');
        return new HttpHeaders();
      }
      console.log("token found return token: ", token);
      return authHeaders.set(
        'Authorization', 'Bearer ' + token,
      );
    }
}
