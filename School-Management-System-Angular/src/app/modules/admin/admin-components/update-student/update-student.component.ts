import { Component, OnInit } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-update-student',
  imports: [],
  templateUrl: './update-student.component.html',
  styleUrl: './update-student.component.css'
})
export class UpdateStudentComponent implements OnInit{
  
  constructor(
    private service: AdminService,
    private activatedRouted: ActivatedRoute,
    private fb: FormBuilder){}

  studentId!:number;
  validateForm: FormGroup;

  ngOnInit(): void {
    this.validateForm = this.fb.group({
      email: ['', Validators.required],
      name: ['', Validators.required],
      password: ['', Validators.required],
      checkPassword: ['', [Validators.required, this.confirmationValidator]],
      fatherName: ['', Validators.required],
      motherName: ['', Validators.required],
      studentClass: ['', Validators.required],
      dateOfBirth: ['', Validators.required],
      address: ['', Validators.required],
      gender: ['', Validators.required]
    })
   this.studentId = this.activatedRouted.snapshot.params['studentId']
   this.getStudentById();
  }
  getStudentById(){
    this.service.getStudentById(this.studentId).subscribe((res)=>{
      console.log(res)
    })
  }
}
