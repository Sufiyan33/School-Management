import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, GuardResult, MaybeAsync, Router, RouterStateSnapshot } from '@angular/router';
import { StorageService } from '../../../services/storage/storage.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: "root"
})

export class adminGuard implements CanActivate  {

  constructor(private router:Router, private snackBar: MatSnackBar){}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if(StorageService.isStudentLoggedIn()){
      this.router.navigateByUrl("/student/dashboard");
      this.snackBar.open("You don't have access to this page", "Close", {
        duration: 5000
      });
      return false;
    }else if(!StorageService.hasToken()){
      StorageService.logout();
      this.router.navigateByUrl("/login");
      this.snackBar.open("You are not logged in", "Close", {
        duration: 5000
      });
      return false;
    }
    return true;
  }
}
