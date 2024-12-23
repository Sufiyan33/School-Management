import { Component, OnInit } from '@angular/core';
import { StudentService } from '../../student-service/student.service';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-get-all-leave',
  imports: [MatProgressSpinnerModule, CommonModule, MatCardModule, RouterModule],
  templateUrl: './get-all-leave.component.html',
  styleUrl: './get-all-leave.component.css'
})
export class GetAllLeaveComponent implements OnInit{

  isSpinning: boolean = false;
  leaves: any;
  
    constructor(private service: StudentService,
    ){}

  ngOnInit(): void {
    this.getAllLeaves()
  }

  getAllLeaves(){
    this.isSpinning = true;
    this.service.getAllAppliedLeaveByStudent().subscribe((res) =>{
      console.log(res);
      this.isSpinning = false;
      this.leaves = res;
    })
  }
}
