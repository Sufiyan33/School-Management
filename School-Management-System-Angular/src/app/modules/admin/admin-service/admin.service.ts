import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Constant } from '../../../const/Constants';
import { StorageService } from '../../../services/storage/storage.service';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addStudent(studentDto: any): Observable<any>{
    return this.http.post<[]>(Constant.BASIC_URL + "api/admin/student", studentDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  getStudentById(studentId: number): Observable<any>{
    return this.http.get<[]>(Constant.BASIC_URL + `api/admin/student/${studentId}`, {
      headers: this.createAuthorizationHeader()
    }) 
  }
  getAllStudent(): Observable<any>{
    return this.http.get<[]>(Constant.BASIC_URL + "api/admin/students", {
      headers: this.createAuthorizationHeader()
    })
  }

  deleteStudent(studentId: any): Observable<any>{
    return this.http.delete<[]>(Constant.BASIC_URL + `api/admin/student/${studentId}`, 
      {
        headers: this.createAuthorizationHeader()
      }
    )
  }

  updateStudent(studentId: number, studentDto: any): Observable<any>{
    return this.http.post<[]>(Constant.BASIC_URL + `api/admin/student/${studentId}`, studentDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  payFee(studentId: number,feeDto: any): Observable<any>{
    return this.http.post<[]>(Constant.BASIC_URL + `api/admin/fee/${studentId}`, feeDto, {
      headers: this.createAuthorizationHeader(),
    });
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
