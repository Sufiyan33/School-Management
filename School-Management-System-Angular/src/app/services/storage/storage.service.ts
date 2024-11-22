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
}
