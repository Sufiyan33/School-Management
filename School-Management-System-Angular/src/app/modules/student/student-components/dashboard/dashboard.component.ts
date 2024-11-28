import { Component } from '@angular/core';

@Component({
  selector: 'app-dashboard',
  host: {'data-id': 'dashboard-instance1'},
  standalone: true,
  imports: [],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css'
})
export class DashboardComponent {

}
