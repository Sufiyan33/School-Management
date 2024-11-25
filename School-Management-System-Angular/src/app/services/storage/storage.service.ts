import { Injectable } from '@angular/core';
import { Constant } from '../../const/Constants';

@Injectable({
  providedIn: 'root'
})
export class StorageService {

  constructor() { }

  public saveUser(user: any){
    window.localStorage.removeItem(Constant.USER);
    window.localStorage.setItem(Constant.USER, JSON.stringify(user));
  }

  public saveToken(token: string){
    window.localStorage.removeItem(Constant.TOKEN);
    window.localStorage.setItem(Constant.TOKEN, token);
  }

  static isAdminLoggedIn(): boolean{
    return false;
  }

  static isStudentLoggedIn(): boolean{
    return false;
  }
}
