import { Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard/dashboard.component';
import { DashboardSummaryComponent } from './dashboard/dashboard-summary/dashboard-summary.component';
import { SignInComponent } from './authentication/sign-in/sign-in.component';
import { ProyectoCrudComponent } from './dashboard/proyecto-crud/proyecto-crud.component';
import { ProyectoTableComponent } from './dashboard/proyecto-table/proyecto-table.component';
import { DashboardConfigComponent } from './dashboard/dashboard-config/dashboard-config.component';
import { UsuariosTableComponent } from './dashboard/usuarios-table/usuarios-table.component';
import { UsuarioCrudComponent } from './dashboard/usuarios-crud/usuarios-crud.component';
import { ActivityTaleComponent } from './dashboard/activity-tale/activity-tale.component';
import { ActividadCrudComponent } from './dashboard/activity-crud/activity-crud.component';

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
                path: "proyectos",
                component: ProyectoTableComponent
            },
            {
                path: "proyectos/crear",
                component: ProyectoCrudComponent
            },
            {
                path: "proyectos/editar/:id",
                component: ProyectoTableComponent
            },
            {
                path: "usuarios",
                component: UsuariosTableComponent
            },
            {
                path: "usuarios/crear",
                component: UsuarioCrudComponent
            },
            {
                path: "usuarios/editar/:id",
                component: UsuarioCrudComponent
            },
            {
                path: "actividades",
                component: ActivityTaleComponent
            },
            {
                path: "actividades/crear",
                component: ActividadCrudComponent
            },
            {
                path: "actividades/editar/:id",
                component: ActividadCrudComponent
            },
            {
                path: "config",
                component: DashboardConfigComponent
            }
        ]
    },

    {
        path: '**',
        pathMatch: 'full',
        component: SignInComponent
    }
];
