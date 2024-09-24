export interface ActividadUsuario {
    id: number;
    usuarioId: number;
    actividadId: number;
    tiempoInvertido: number; // tiempo en minutos
    fecha: Date;
  }