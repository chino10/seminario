import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Observacion } from '../modelos/Observacion';

@Injectable({
  providedIn: 'root'
})
export class ObservacionService {
  observaciones$: Observable<Observacion[]>;
  observacion: Observacion = {
    fecha: '',
    descripcion: ''
  };

  constructor(private http: HttpClient) {
    this.observaciones$ = of(); // inicializa como un Observable vacío
    this.obtenerObservaciones();
  }

  obtenerObservaciones() {
    return this.observaciones$ = this.http.get<Observacion[]>('/api/v1/observaciones');
  }

  obtenerObservacionPorId(id: number): Observable<Observacion> {
    return this.http.get<Observacion>(`/api/v1/observaciones/${id}`);
  }

  crearObservacion() {
    this.http.post('/api/v1/observaciones', this.observacion).subscribe(() => {
      console.log('Observacion creado exitosamente!');
      this.obtenerObservaciones();
    });
  }

  actualizarObservacion(id: number) {
    this.http.put(`/api/v1/observaciones/${id}`, this.observacion).subscribe(() => {
      console.log('Observacion actualizado exitosamente!');
      this.obtenerObservaciones();
    });
  }

  eliminarObservacion(id: number) {
    this.http.delete(`/api/v1/observaciones/${id}`).subscribe(() => {
      console.log('Observacion eliminado exitosamente!');
      this.obtenerObservaciones();
    });
  }
}