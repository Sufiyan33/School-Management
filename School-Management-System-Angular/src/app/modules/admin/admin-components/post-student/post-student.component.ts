import { Component } from '@angular/core';
import { MatDatepicker, MatDatepickerToggle } from '@angular/material/datepicker';
import { MatFormField, MatLabel } from '@angular/material/form-field';
import { MatProgressSpinnerModule, MatSpinner } from '@angular/material/progress-spinner';
import { MatOption, MatSelect } from '@angular/material/select';

@Component({
  selector: 'app-post-student',
  standalone: true,
  imports: [MatProgressSpinnerModule, MatFormField, MatLabel, MatSelect, MatOption, MatDatepicker, MatDatepickerToggle],
  templateUrl: './post-student.component.html',
  styleUrl: './post-student.component.css'
})
export class PostStudentComponent {

}
