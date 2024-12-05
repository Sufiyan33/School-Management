import { CommonModule } from '@angular/common';
import { Component } from '@angular/core';
import { MatDatepicker, MatDatepickerModule, MatDatepickerToggle } from '@angular/material/datepicker';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatOption, MatSelect, MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-post-student',
  standalone: true,
  imports: [MatProgressSpinnerModule, MatFormFieldModule, MatLabel, MatSelectModule, 
    MatOption, MatDatepickerModule, MatDatepickerToggle, CommonModule],
  templateUrl: './post-student.component.html',
  styleUrl: './post-student.component.css'
})
export class PostStudentComponent {

  CLASS: string[]= ["play", "1st", "2nd","3rd","4th","5th", "6th", "7th","8th", "9th", "10th", "11th", "12th"];
  GENDER: string[]= ["Male", "Female", "Not specified"];
  isSpinning: boolean;

}
