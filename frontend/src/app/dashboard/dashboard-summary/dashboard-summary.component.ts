import { Component, inject, OnInit, signal } from '@angular/core';
import { ActivityCardComponent } from "../activity-card/activity-card.component";
import { ProyectoCrudComponent } from "../proyecto-crud/proyecto-crud.component";
import { EasyCrudService } from '../../reusable/core/services/easy-crud.service';
import { Actividad } from '../../reusable/models/actividad/actividad.interface';
import { AuthService } from '../../reusable/core/services/auth.service';


@Component({
  selector: 'app-dashboard-summary',
  standalone: true,
  imports: [ActivityCardComponent, ProyectoCrudComponent],
  templateUrl: './dashboard-summary.component.html',
  styleUrl: './dashboard-summary.component.scss'
})
export class DashboardSummaryComponent implements OnInit {

  easyCrud = inject(EasyCrudService)

  actividadesPendientes = signal<Actividad[]>([]);
  actividadesProceso = signal<Actividad[]>([]);
  actividadesTerminadas = signal<Actividad[]>([]);

  
  authService = inject(AuthService);

 

  get role(){
    return this.authService.userRole
  }


  ngOnInit(): void {
    this.actividadesPendientes.set([
      {
        id: 1,
        nombre: 'Crear Un BackEnd',
        proyectoId: 1,
        estadoId: 1
      }
    ])


    // this.easyCrud.httpGetList('actividad').subscribe({
    //   next: (res) => {
    //     console.log(res);
    //   }
    // })
  }

}
