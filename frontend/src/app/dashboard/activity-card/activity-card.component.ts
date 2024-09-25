import { Component, inject, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';
import { Actividad } from '../../reusable/models/actividad/actividad.interface';
import { AuthService } from '../../reusable/core/services/auth.service';

@Component({
  selector: 'app-activity-card',
  standalone: true,
  imports: [MatIconModule],
  templateUrl: './activity-card.component.html',
  styleUrl: './activity-card.component.scss'
})
export class ActivityCardComponent {

  @Input()actividad!:Actividad



}
