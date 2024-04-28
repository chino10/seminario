import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { DetalleTramite } from '../modelos/DetalleTramite';

@Injectable({
  providedIn: 'root'
})
export class DetalleTramiteService {
  private url = 'http://localhost:8080';
  detalleTramites$: Observable<DetalleTramite[]>;
  detalleTramite: DetalleTramite = {
    listaClientes: null,
    listaDocumentos: null,
    estado: null,
    listaUsuarios: null,
    consulado: null,
    fechaInicio: '',
    fechaFin: '',
    tramite: null,
    listaObservaciones: null
  };

  constructor(private http: HttpClient) {
    this.detalleTramites$ = of(); // inicializa como un Observable vacío
    this.obtenerDetallesTramite();
  }

  obtenerDetallesTramite() {
    console.log(this.url + '/api/v1/detalles-tramite');
    return this.detalleTramites$ = this.http.get<DetalleTramite[]>(this.url + '/api/v1/detalles-tramite');
  }

  obtenerDetalleTramitePorId(id: number): Observable<DetalleTramite> {
    console.log(this.url + '/api/v1/detalles-tramite');
    return this.http.get<DetalleTramite>(this.url + `/api/v1/detalles-tramite/${id}`);
  }

  crearDetalleTramite() {
    this.http.post(this.url + '/api/v1/detalles-tramite', this.detalleTramite).subscribe(() => {
      console.log('Detalle de tramite creado exitosamente!');
      this.obtenerDetallesTramite();
    });
  }

  actualizarDetalleTramite(id: number) {
    this.http.put(this.url + `/api/v1/detalles-tramite/${id}`, this.detalleTramite).subscribe(() => {
      console.log('Detalle de tramite actualizado exitosamente!');
      this.obtenerDetallesTramite();
    });
  }

  eliminarDetalleTramite(id: number) {
    this.http.delete(this.url + `/api/v1/detalles-tramite/${id}`).subscribe(() => {
      console.log('Detalle de tramite eliminado exitosamente!');
      this.obtenerDetallesTramite();
    });
  }
}