import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class StudentService {

  constructor(private http: HttpClient) { }

  getStudentById(studentId: number): Observable<any>{
      return this.http.get<[]>(Constant.BASIC_URL + `api/admin/student/${studentId}`, {
        headers: this.createAuthorizationHeader()
      }) 
    }
}
