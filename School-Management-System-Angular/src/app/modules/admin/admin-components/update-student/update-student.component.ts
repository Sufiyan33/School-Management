import { Component } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-update-student',
  imports: [],
  templateUrl: './update-student.component.html',
  styleUrl: './update-student.component.css'
})
export class UpdateStudentComponent {
  
  constructor(
    private service: AdminService,
    private activatedRouted: ActivatedRoute){}

  studentId: number = this.activatedRouted.snapshot.params['studentId']

  getStudentById(){
    this.service.getStudentById(this.studentId).subscribe((res)=>{
      console.log(res)
    })
  }
}
