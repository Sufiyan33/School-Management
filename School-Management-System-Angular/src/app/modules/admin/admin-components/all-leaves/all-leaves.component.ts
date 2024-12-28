import { Component } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';
import { RouterModule } from '@angular/router';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-all-leaves',
  standalone: true,
  imports: [MatProgressSpinnerModule, CommonModule, MatCardModule, RouterModule],
  templateUrl: './all-leaves.component.html',
  styleUrl: './all-leaves.component.css'
})
export class AllLeavesComponent {

  isSpinning: boolean = false;
  leaves: any;
    
  constructor(private service: AdminService,
  ){}
  
  ngOnInit(): void {
    this.getAllLeaves()
  }
  
  getAllLeaves(){
    this.isSpinning = true;
    this.service.getAllAppliedLeaves().subscribe((res) =>{
      console.log(res);
      this.isSpinning = false;
      this.leaves = res;
    })
  }

  /*changeLeaveStatus(leaveId: number, status: string){
    this.isSpinning = true;
    this.service.changeLeaveStatus(leaveId, status).subscribe()
  }*/
}
