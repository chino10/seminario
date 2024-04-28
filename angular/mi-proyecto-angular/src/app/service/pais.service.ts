import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Pais } from '../modelos/Pais';

@Injectable({
  providedIn: 'root'
})
export class PaisService {
  paises$: Observable<Pais[]>;
  pais: Pais = {
    nombre: ''
  };

  constructor(private http: HttpClient) {
    this.paises$ = of(); // inicializa como un Observable vacío
    this.obtenerPaises();
  }

  obtenerPaises() {
    return this.paises$ = this.http.get<Pais[]>('/api/v1/paises');
  }

  obtenerPaisPorId(id: number): Observable<Pais> {
    return this.http.get<Pais>(`/api/v1/paises/${id}`);
  }

  crearPais() {
    this.http.post('/api/v1/paises', this.pais).subscribe(() => {
      console.log('Pais creado exitosamente!');
      this.obtenerPaises();
    });
  }

  actualizarPais(id: number) {
    this.http.put(`/api/v1/paises/${id}`, this.pais).subscribe(() => {
      console.log('Pais actualizado exitosamente!');
      this.obtenerPaises();
    });
  }

  eliminarPais(id: number) {
    this.http.delete(`/api/v1/paises/${id}`).subscribe(() => {
      console.log('Pais eliminado exitosamente!');
      this.obtenerPaises();
    });
  }
}
