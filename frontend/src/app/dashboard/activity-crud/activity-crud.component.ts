import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-actividad-crud',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatIconModule, ReactiveFormsModule, MatButtonModule],
  templateUrl: './actividad-crud.component.html',
  styleUrls: ['./actividad-crud.component.scss']
})
export class ActividadCrudComponent {

  hide = true;

  // authService = inject(AuthService);

  actividadForm = new FormGroup({
    nombre: new FormControl('', [Validators.required]),  // Campo obligatorio
    proyecto_id: new FormControl('', []),  // Campo opcional
    estado_id: new FormControl('', [Validators.required]),  // Campo obligatorio
  });

  submit() {
    if (this.actividadForm.valid) {
      const actividad = this.actividadForm.value;
      console.log(actividad);
    } else {
      console.error('Formulario inválido');
    }
  }
}
