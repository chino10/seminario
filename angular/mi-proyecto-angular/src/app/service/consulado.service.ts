import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Consulado } from '../modelos/Consulado';

@Injectable({
  providedIn: 'root'
})
export class ConsuladoService {
  consulados$: Observable<Consulado[]>;
  consulado: Consulado = {
    pais: null,
    domicilio: '',
    ciudad: '',
    provincia: ''
  };

  constructor(private http: HttpClient) {
    this.consulados$ = of(); // inicializa como un Observable vacío
    this.obtenerConsulados();
  }

  obtenerConsulados() {
    return this.consulados$ = this.http.get<Consulado[]>('/api/v1/consulados');
  }

  obtenerConsuladoPorId(id: number): Observable<Consulado> {
    return this.http.get<Consulado>(`/api/v1/consulados/${id}`);
  }

  crearConsulado() {
    this.http.post('/api/v1/consulados', this.consulado).subscribe(() => {
      console.log('Consulado creado exitosamente!');
      this.obtenerConsulados();
    });
  }

  actualizarConsulado(id: number) {
    this.http.put(`/api/v1/consulados/${id}`, this.consulado).subscribe(() => {
      console.log('Consulado actualizado exitosamente!');
      this.obtenerConsulados();
    });
  }

  eliminarConsulado(id: number) {
    this.http.delete(`/api/v1/consulados/${id}`).subscribe(() => {
      console.log('Consulado eliminado exitosamente!');
      this.obtenerConsulados();
    });
  }
}