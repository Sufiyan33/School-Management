import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDatepicker, MatDatepickerModule, MatDatepickerToggle } from '@angular/material/datepicker';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatOption, MatSelect, MatSelectModule } from '@angular/material/select';
import { AdminService } from '../../admin-service/admin.service';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-post-student',
  standalone: true,
  imports: [MatProgressSpinnerModule, MatFormFieldModule, MatLabel, MatSelectModule, 
    MatOption, MatDatepickerModule, MatDatepickerToggle, CommonModule, ReactiveFormsModule, FormsModule,
    MatButtonModule, MatInputModule],
  templateUrl: './post-student.component.html',
  styleUrl: './post-student.component.css'
})
export class PostStudentComponent implements OnInit{

  CLASS: string[]= ["play", "1st", "2nd","3rd","4th","5th", "6th", "7th","8th", "9th", "10th", "11th", "12th"];
  GENDER: string[]= ["Male", "Female", "Not specified"];
  isSpinning: boolean;
  validateForm: FormGroup;

  constructor(
    private service: AdminService,
    private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: ['', Validators.required],
      name: ['', Validators.required],
      password: ['', Validators.required],
      checkPassword: ['', [Validators.required, this.confirmationValidator]],
      fatherName: ['', Validators.required],
      motherName: ['', Validators.required],
      studentClass: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      address: ['', Validators.required],
      gender: ['', Validators.required]
    })
  }

  postStudent(){
    console.log(this.validateForm.value)
    console.log('Authorization Header:', this.service.createAuthorizationHeader().get('Authorization'));
    this.isSpinning = true;
    this.service.addStudent(this.validateForm.value).subscribe((res:any)=>{
      console.log(res);
    })
  }

  confirmationValidator = (control: FormControl):{
    [s: string]:boolean
  } =>{
    if(!control.value){
      return {required: true}
    }else if(control.value !== this.validateForm.controls['password'].value){
      return {confirm: true, error: true};
    }
    return {};
  }
}
