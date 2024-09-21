import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-proyecto-crud',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatIconModule, ReactiveFormsModule, MatButtonModule],
  templateUrl: './proyecto-crud.component.html',
  styleUrl: './proyecto-crud.component.scss'
})
export class ProyectoCrudComponent {

  hide = true;

  //authService = inject(AuthService);

  projectForm = new FormGroup({
    nombre: new FormControl('', [Validators.required]),
    description: new FormControl('', [Validators.required]),
    fecha_inicio: new FormControl('', [Validators.required]),
    fecha_fin: new FormControl('', [Validators.required]),
  });
  

  submit(){
    if (this.projectForm.valid) {
      const project = this.projectForm.value;
      console.log(project);
      
      //this.authService.httpAccount(account, 'singIn')
    } else {
      console.error('Form is invalid');
    }
  }

  compararFecha(){
    const fechaInicio = this.projectForm.get(' fecha_inicio')!.value;
    const fechaFin = this.projectForm.get(' fecha_fin')!.value;
    return fechaFin && fechaInicio && new Date(fechaFin) > new Date(fechaInicio) ? null : { fechaInvalida: true };
  }

}
