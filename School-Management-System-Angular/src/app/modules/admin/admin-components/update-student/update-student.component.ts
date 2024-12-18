import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatOption, MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule, MatDatepickerToggle } from '@angular/material/datepicker';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-update-student',
  imports: [MatProgressSpinnerModule, MatFormFieldModule, MatLabel, MatSelectModule, 
      MatOption, MatDatepickerModule, MatDatepickerToggle, CommonModule, ReactiveFormsModule, FormsModule,
      MatButtonModule, MatInputModule],
  templateUrl: './update-student.component.html',
  styleUrl: './update-student.component.css'
})
export class UpdateStudentComponent implements OnInit{
  
  constructor(
    private service: AdminService,
    private activatedRouted: ActivatedRoute,
    private fb: FormBuilder){}

  studentId!:number;
  validateForm: FormGroup;
  isSpinning: boolean;
  CLASS: string[]= ["play", "1st", "2nd","3rd","4th","5th", "6th", "7th","8th", "9th", "10th", "11th", "12th"];
  GENDER: string[]= ["Male", "Female", "Not specified"];

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: ['', Validators.required],
      name: ['', Validators.required],
      fatherName: ['', Validators.required],
      motherName: ['', Validators.required],
      studentClass: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      address: ['', Validators.required],
      gender: ['', Validators.required]
    })
   this.studentId = this.activatedRouted.snapshot.params['studentId']
   this.getStudentById();
  }
  getStudentById(){
    this.service.getStudentById(this.studentId).subscribe((res)=>{
      console.log(res)
    })
  }
}
