import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { StudentService } from '../../student-service/student.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-apply-leave',
  imports: [MatProgressSpinnerModule, MatFormFieldModule, MatLabel, 
          CommonModule, ReactiveFormsModule, FormsModule, MatInputModule, MatButtonModule],
  templateUrl: './apply-leave.component.html',
  styleUrl: './apply-leave.component.css'
})
export class ApplyLeaveComponent implements OnInit{

  isSpinning: boolean = false;
  validateForm: FormGroup;

  constructor(private service: StudentService,
    private fb: FormBuilder,
    private snackBar: MatSnackBar,
    private router: Router
  ){}

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      subject: ['', Validators.required],
      body: ['', Validators.required]
    });
  }

  applyLeave(){
    this.isSpinning = true;
    console.log(this.validateForm.value);
    this.service.applyLeave(this.validateForm.value).subscribe((res)=>{
      console.log(res);
      this.isSpinning = false;
      if(res.id != null){
        this.snackBar.open("Leave submitted successfully...", "Close", {duration: 5000});
        this.router.navigateByUrl('student/dashboard');
      }else{
        this.snackBar.open("Something went wrong!", "ERROR", {duration: 5000});
      }
    })
  }
}
