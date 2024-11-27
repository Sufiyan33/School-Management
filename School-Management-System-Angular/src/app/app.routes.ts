import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { noAuthGuard } from './auth/guards/noAuth-guard/no-auth.guard';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
        canActivate: [noAuthGuard]

    },
    {
        path: 'admin', 
        loadChildren: ()=> import("./modules/admin/admin.module").then(m => m.AdminModule)
    },
    {
        path: 'student',
        loadChildren: ()=> import("./modules/student/student.module").then(m => m.StudentModule)
    }
];
