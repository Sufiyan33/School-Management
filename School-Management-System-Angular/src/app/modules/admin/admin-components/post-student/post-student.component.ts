import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatDatepicker, MatDatepickerModule, MatDatepickerToggle } from '@angular/material/datepicker';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatOption, MatSelect, MatSelectModule } from '@angular/material/select';
import { AdminService } from '../../admin-service/admin.service';

@Component({
  selector: 'app-post-student',
  standalone: true,
  imports: [MatProgressSpinnerModule, MatFormFieldModule, MatLabel, MatSelectModule, 
    MatOption, MatDatepickerModule, MatDatepickerToggle, CommonModule, ReactiveFormsModule, FormsModule],
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
      email: ['null', Validators.required],
      name: ['null', Validators.required],
      password: ['null', Validators.required],
      checkPassword: ['null', Validators.required],
      fatherName: ['null', Validators.required],
      motherName: ['null', Validators.required],
      studentClass: ['null', Validators.required],
      dateOfBirth: ['null', Validators.required],
      address: ['null', Validators.required],
      gender: ['null', Validators.required]
    })
  }

  postStudent(){
    console.log(this.validateForm.value)
  }
}
