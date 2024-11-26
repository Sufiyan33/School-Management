import { Component, OnInit } from '@angular/core';
import { NavigationEnd, Router, RouterLink, RouterOutlet } from '@angular/router';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { StorageService } from './services/storage/storage.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatSlideToggleModule, MatToolbarModule, 
    MatButtonModule, RouterLink, ReactiveFormsModule, MatInputModule, MatFormFieldModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit{
  title = 'School-Management-System-Angular';

  isAdminLoggedIn: boolean;
  isStudentLoggedIn: boolean;

  constructor(private router: Router){}

  ngOnInit(): void {
    this.getLoggedInUserStatus();
    this.router.events.subscribe(event =>{
      if(event instanceof NavigationEnd){
        this.getLoggedInUserStatus();
      }
    })
  }

  private getLoggedInUserStatus(): void{
    this.isAdminLoggedIn = StorageService.isAdminLoggedIn();
    this.isStudentLoggedIn = StorageService.isStudentLoggedIn();
  }

  logout(){
    StorageService.logout();
    this.router.navigateByUrl("/login");
  }
}
