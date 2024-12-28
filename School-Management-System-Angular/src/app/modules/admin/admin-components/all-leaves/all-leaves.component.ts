import { Component } from '@angular/core';
import { AdminService } from '../../admin-service/admin.service';

@Component({
  selector: 'app-all-leaves',
  imports: [],
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
}
