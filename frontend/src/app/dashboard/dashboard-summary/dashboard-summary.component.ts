import { Component } from '@angular/core';
import { ActivityCardComponent } from "../activity-card/activity-card.component";
import { ProyectoCrudComponent } from "../proyecto-crud/proyecto-crud.component";


@Component({
  selector: 'app-dashboard-summary',
  standalone: true,
  imports: [ActivityCardComponent, ProyectoCrudComponent],
  templateUrl: './dashboard-summary.component.html',
  styleUrl: './dashboard-summary.component.scss'
})
export class DashboardSummaryComponent {

  actividades = [
    {
      nombre: "tarea 1",
      descripcion: "esto es una tarea", 
      estado: "Activo",

    }
  ]

}
