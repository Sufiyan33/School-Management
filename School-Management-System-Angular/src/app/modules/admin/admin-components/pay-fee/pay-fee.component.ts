import { Component } from '@angular/core';
import { MatOption } from '@angular/material/core';
import { MatFormFieldModule, MatLabel } from '@angular/material/form-field';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import { MatSelectModule } from '@angular/material/select';

@Component({
  selector: 'app-pay-fee',
  imports: [MatProgressSpinnerModule, MatFormFieldModule, MatLabel, MatSelectModule, 
      MatOption],
  templateUrl: './pay-fee.component.html',
  styleUrl: './pay-fee.component.css'
})
export class PayFeeComponent {

}
