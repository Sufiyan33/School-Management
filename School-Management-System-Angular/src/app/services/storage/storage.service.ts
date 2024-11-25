import { Injectable } from '@angular/core';
import { Constant } from '../../const/Constants';
import { Token } from '@angular/compiler';

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
    if(this.getToken() == null){
      return true;
    }
    const role: string = this.getUsersRole();
    return role== "ADMIN";
  }

  static isStudentLoggedIn(): boolean{
    return false;
  }

  static getToken(): string{
    return window.localStorage.getItem(Constant.TOKEN);
  }

  static getUsersRole(): string{
    return null;
  }

}
