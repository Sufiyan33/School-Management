import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Constant } from '../../../const/Constants';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addStudent(studentDto: any){
    this.http.post<[]>(Constant.BASIC_URL + "api/admin/student", studentDto);
  }
}
