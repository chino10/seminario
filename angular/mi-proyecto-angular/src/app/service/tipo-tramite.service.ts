import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { TipoTramite } from '../modelos/TipoTramite';

@Injectable({
  providedIn: 'root'
})
export class TipoTramiteService {
  tipoTramites$: Observable<TipoTramite[]>;
  tipoTramite: TipoTramite = {
    nombre: '',
    descripcion: ''
  };

  constructor(private http: HttpClient) {
    this.tipoTramites$ = of(); // inicializa como un Observable vacío
    this.obtenerTiposTramite();
  }

  obtenerTiposTramite() {
    return this.tipoTramites$ = this.http.get<TipoTramite[]>('/api/v1/tipos-tramite');
  }

  obtenerTipoTramitePorId(id: number): Observable<TipoTramite> {
    return this.http.get<TipoTramite>(`/api/v1/tipos-tramite/${id}`);
  }

  crearTipoTramite() {
    this.http.post('/api/v1/tipos-tramite', this.tipoTramite).subscribe(() => {
      console.log('Tipo de tramite creado exitosamente!');
      this.obtenerTiposTramite();
    });
  }

  actualizarTipoTramite(id: number) {
    this.http.put(`/api/v1/tipos-tramite/${id}`, this.tipoTramite).subscribe(() => {
      console.log('Tipo de tramite actualizado exitosamente!');
      this.obtenerTiposTramite();
    });
  }

  eliminarTipoTramite(id: number) {
    this.http.delete(`/api/v1/tipos-tramite/${id}`).subscribe(() => {
      console.log('Tipo de tramite eliminado exitosamente!');
      this.obtenerTiposTramite();
    });
  }
}