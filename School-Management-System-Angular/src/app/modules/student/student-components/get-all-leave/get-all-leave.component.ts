import { Component, OnInit } from '@angular/core';
import { StudentService } from '../../student-service/student.service';

@Component({
  selector: 'app-get-all-leave',
  imports: [],
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

  getAllLeaves(){}
}
