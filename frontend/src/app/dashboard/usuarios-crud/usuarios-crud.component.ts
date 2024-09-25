import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-usuario-crud',
  standalone: true,
  imports: [MatFormFieldModule, MatInputModule, MatIconModule, ReactiveFormsModule, MatButtonModule],
  templateUrl: './usuarios-crud.component.html',
  styleUrls: ['./usuarios-crud.component.scss']
})
export class UsuarioCrudComponent {

  hide = true;

  //authService = inject(AuthService);

  usuarioForm = new FormGroup({
    nombre: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(6)]),
    grupo_id: new FormControl('', []),  // No es obligatorio
    rol_id: new FormControl('', [Validators.required]),
  });

  submit() {
    if (this.usuarioForm.valid) {
      const usuario = this.usuarioForm.value;
      console.log(usuario);
      
    } else {
      console.error('Formulario inválido');
    }
  }
}
