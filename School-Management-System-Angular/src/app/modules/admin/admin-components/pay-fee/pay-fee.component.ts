import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { MatOption } from '@angular/material/core';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-pay-fee',
  imports: [MatProgressSpinnerModule, MatFormFieldModule, MatLabel, MatSelectModule, 
      MatOption],
  templateUrl: './pay-fee.component.html',
  styleUrl: './pay-fee.component.css'
})
export class PayFeeComponent implements OnInit{

  constructor(private service: AdminService,
    private activatedRouted: ActivatedRoute,
    private fb: FormBuilder
  ){}

  ngOnInit(): void {
    this.studentId = this.activatedRouted.snapshot.params['studentId']
  }

  studentId!:number;
  validateForm: FormGroup;
  isSpinning: boolean = false;
  MONTH: string[] = ["January", "February", "March", "April", "May", "June", 
    "July", "August", "September", "October", "November", "December"];

  payFee(){

  }
}
