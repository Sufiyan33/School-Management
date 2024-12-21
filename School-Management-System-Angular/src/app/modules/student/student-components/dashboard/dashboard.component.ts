import { Component, OnInit } from '@angular/core';
import { StudentService } from '../../student-service/student.service';
import { RouterModule } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-dashboard',
  host: {'data-id': 'dashboard-instance1'},
  standalone: true,
  imports: [MatCardModule, CommonModule, MatButtonModule, RouterModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  students: any;
  constructor(private service: StudentService){}

  ngOnInit(): void {
    this.getStudentById();
  }

  getStudentById(){
    this.service.getStudentById().subscribe((res)=>{
      console.log(res);
      const studentDto = res.studentDto;
      // Ensure `students` is always an array
      this.students = Array.isArray(studentDto) ? studentDto : [studentDto];
    })
  }

}
