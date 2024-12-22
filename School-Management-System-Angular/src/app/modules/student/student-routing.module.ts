import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './student-components/dashboard/dashboard.component';
import { studentGuard } from '../../auth/guards/student-guard/student.guard';
import { ApplyLeaveComponent } from './student-components/apply-leave/apply-leave.component';

const routes: Routes = [
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [studentGuard]
  },
  {
    path: 'leave',
    component: ApplyLeaveComponent,
    canActivate: [studentGuard]
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class StudentRoutingModule { }
