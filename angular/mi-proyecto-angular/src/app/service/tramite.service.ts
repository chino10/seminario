import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Tramite } from '../modelos/Tramite';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class TramiteService {
  tramites$: Observable<Tramite[]>;
  tramite: Tramite = {
    consulado: null,
    tipoTramite: null,
    importe: null
  };

  constructor(private http: HttpClient) {
    this.tramites$ = of(); // inicializa como un Observable vacío
    this.obtenerTramites();
  }

  obtenerTramites() {
    return this.tramites$ = this.http.get<Tramite[]>('/api/v1/tramites');
  }

  obtenerTramitePorId(id: number): Observable<Tramite> {
    return this.http.get<Tramite>(`/api/v1/tramites/${id}`);
  }

  crearTramite() {
    this.http.post('/api/v1/tramites', this.tramite).subscribe(() => {
      console.log('Tramite creado exitosamente!');
      this.obtenerTramites();
    });
  }

  actualizarTramite(id: number) {
    this.http.put(`/api/v1/tramites/${id}`, this.tramite).subscribe(() => {
      console.log('Tramite actualizado exitosamente!');
      this.obtenerTramites();
    });
  }

  eliminarTramite(id: number) {
    this.http.delete(`/api/v1/tramites/${id}`).subscribe(() => {
      console.log('Tramite eliminado exitosamente!');
      this.obtenerTramites();
    });
  }
}
