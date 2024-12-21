import { Component, OnInit } from '@angular/core';
import { StudentService } from '../../student-service/student.service';

@Component({
  selector: 'app-dashboard',
  host: {'data-id': 'dashboard-instance1'},
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent implements OnInit{

  constructor(private service: StudentService){}

  ngOnInit(): void {
    this.getStudentById();
  }

  getStudentById(){
    this.service.getStudentById().subscribe((res)=>{
      console.log(res);
    })
  }

}
