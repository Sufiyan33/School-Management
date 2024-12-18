import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { MatButtonModule } from '@angular/material/button';
import { MatSnackBar } from '@angular/material/snack-bar';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-all-students',
  standalone: true,
  imports: [MatCardModule, CommonModule, MatButtonModule, RouterModule],
  templateUrl: './all-students.component.html',
  styleUrl: './all-students.component.css'
})
export class AllStudentsComponent implements OnInit {

  students: any;
  constructor(private service: AdminService, 
    private snackBar: MatSnackBar){}

  ngOnInit(): void {
    this.getAllStudents();
  }

  getAllStudents(){
    this.service.getAllStudent().subscribe((res)=>{
      console.log(res);
      this.students = res;
    })
  }

  deleteStudent(studentId: number){
    this.service.deleteStudent(studentId).subscribe((res) =>{
      console.log(res);
      this.getAllStudents();
      this.snackBar.open("Student Deleted Successfully", "Close", {
        duration: 5000
      })
    })
  }
}
