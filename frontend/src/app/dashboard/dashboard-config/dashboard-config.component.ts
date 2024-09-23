import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-dashboard-config',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatIconModule, ReactiveFormsModule, MatButtonModule],
  templateUrl: './dashboard-config.component.html',
  styleUrl: './dashboard-config.component.scss'
})
export class DashboardConfigComponent {

  hide = true;

  // authService = inject(AuthService); // Ejemplo para servicio de autenticación

  dashboardForm = new FormGroup({
    proyecto_id: new FormControl('', [Validators.required]), // ID del proyecto es obligatorio
    total_horas_trabajadas: new FormControl('', [Validators.required, Validators.min(1)]), // Total de horas trabajadas es obligatorio
    fecha: new FormControl('', [Validators.required]), // Fecha es obligatoria
  });

  submit() {
    if (this.dashboardForm.valid) {
      const dashboard = this.dashboardForm.value;
      console.log(dashboard);
      
    } else {
      console.error('Formulario inválido');
    }
  }
}
