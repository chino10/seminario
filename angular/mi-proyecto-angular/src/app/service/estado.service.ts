import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Estado } from '../modelos/Estado';

@Injectable({
  providedIn: 'root'
})
export class EstadoService {
  estados$: Observable<Estado[]>;
  estado: Estado = {
    nombre: '',
    descripcion: ''
  };

  constructor(private http: HttpClient) {
    this.estados$ = of(); // inicializa como un Observable vacío
    this.obtenerEstados();
  }

  obtenerEstados() {
    return this.estados$ = this.http.get<Estado[]>('/api/v1/estados');
  }

  obtenerEstadoPorId(id: number): Observable<Estado> {
    return this.http.get<Estado>(`/api/v1/estados/${id}`);
  }

  crearEstado() {
    this.http.post('/api/v1/estados', this.estado).subscribe(() => {
      console.log('Estado creado exitosamente!');
      this.obtenerEstados();
    });
  }

  actualizarEstado(id: number) {
    this.http.put(`/api/v1/estados/${id}`, this.estado).subscribe(() => {
      console.log('Estado actualizado exitosamente!');
      this.obtenerEstados();
    });
  }

  eliminarEstado(id: number) {
    this.http.delete(`/api/v1/estados/${id}`).subscribe(() => {
      console.log('Estado eliminado exitosamente!');
      this.obtenerEstados();
    });
  }
}