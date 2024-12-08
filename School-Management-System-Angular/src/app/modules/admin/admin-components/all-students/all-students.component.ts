import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';

@Component({
  selector: 'app-all-students',
  standalone: true,
  imports: [],
  templateUrl: './all-students.component.html',
  styleUrl: './all-students.component.css'
})
export class AllStudentsComponent implements OnInit {

  constructor(private service: AdminService){}

  ngOnInit(): void {
    this.getAllStudents();
  }

  getAllStudents(){
    this.service.getAllStudent().subscribe((res)=>{
      console.log(res);
    })
  }
}
