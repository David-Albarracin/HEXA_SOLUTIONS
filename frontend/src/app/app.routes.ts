import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';
import { DashboardSummaryComponent } from './dashboard/dashboard-summary/dashboard-summary.component';
import { SignInComponent } from './authentication/sign-in/sign-in.component';
import { ProyectoCrudComponent } from './dashboard/proyecto-crud/proyecto-crud.component';

export const routes: Routes = [
    {
        path: '',
        redirectTo: '/login',
        pathMatch: 'full'
    },

    {
        path: 'login',
        component: SignInComponent
    },

    {
        path: 'dashboard',
        component: DashboardComponent,
        children: [
            {
                path: "",
                component: DashboardSummaryComponent
            },
            {
                path: "proyectos/crear",
                component: ProyectoCrudComponent
            }
        ]
    },

    {
        path: '**',
        pathMatch: 'full',
        component: SignInComponent
    }
];
