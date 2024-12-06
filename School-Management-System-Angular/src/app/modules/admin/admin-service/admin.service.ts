import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Constant } from '../../../const/Constants';
import { StorageService } from '../../../services/storage/storage.service';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  constructor(private http: HttpClient) { }

  addStudent(studentDto: any){
    return this.http.post<[]>(Constant.BASIC_URL + "api/admin/student", studentDto, {
      headers: this.createAuthorizationHeader(),
    });
  }

  createAuthorizationHeader(): HttpHeaders{
    let authHeaders: HttpHeaders = new HttpHeaders();
    return authHeaders.set(
      'Authorization', "Bearer " + StorageService.getToken()
    );
  }

}
