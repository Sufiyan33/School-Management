import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatOption } from '@angular/material/core';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-pay-fee',
  standalone: true,
  imports: [MatProgressSpinnerModule, MatFormFieldModule, MatLabel, MatSelectModule, 
      MatOption, CommonModule, ReactiveFormsModule, FormsModule, MatInputModule, MatButtonModule],
  templateUrl: './pay-fee.component.html',
  styleUrl: './pay-fee.component.css'
})
export class PayFeeComponent implements OnInit{

  constructor(private service: AdminService,
    private activatedRouted: ActivatedRoute,
    private fb: FormBuilder
  ){}

  studentId!:number;
  validateForm: FormGroup;
  isSpinning: boolean = false;
  MONTH: string[] = ["January", "February", "March", "April", "May", "June", 
    "July", "August", "September", "October", "November", "December"];

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      amount: ['', Validators.required],
      month: ['', Validators.required],
      givenBy: ['', Validators.required],
      description: ['', Validators.required],
    })
    this.studentId = this.activatedRouted.snapshot.params['studentId']
  }

  payFee(){
    this.service.payFee(this.studentId, this.validateForm.value).subscribe((res)=>{
      console.log(res);
    })
  }
}
