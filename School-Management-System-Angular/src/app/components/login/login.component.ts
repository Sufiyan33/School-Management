import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../services/auth/auth.service';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInput } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { StorageService } from '../../services/storage/storage.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [MatFormFieldModule, ReactiveFormsModule, MatInput,MatButtonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent implements OnInit{

  loginForm: FormGroup | undefined;
  constructor(private service: AuthService, 
    private fb: FormBuilder,
    private router: Router,
    private snakBar: MatSnackBar){}

  ngOnInit(){
    this.loginForm = this.fb.group({
      email: ['', Validators.required],
      password: ['', Validators.required]
    })
  }

  login(){
    //console.log(this.loginForm.value);
    this.service.login(
      this.loginForm.get(['email'])!.value,
      this.loginForm.get(['password'])!.value
    ).subscribe((response) =>{
      console.log(response);
      if(StorageService.isAdminLoggedIn()){
        this.router.navigateByUrl("admin/dashboard");
      }else if(StorageService.isStudentLoggedIn()){
        this.router.navigateByUrl("student/dashboard");
      }
    }),
    //this.loginForm.reset();
    error =>{
      if(error.status == 406){
        this.snakBar.open("User is not active", "Close", {
          duration: 5000
        });
      }else{
        this.snakBar.open("Bad credentials", "close", {
          duration: 5000
        });
      }
    }
  }
}
