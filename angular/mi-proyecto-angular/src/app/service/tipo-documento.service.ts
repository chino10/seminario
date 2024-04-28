import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { TipoDocumento } from '../modelos/TipoDocumento';

@Injectable({
  providedIn: 'root'
})
export class TipoDocumentoService {
  tipoDocumentos$: Observable<TipoDocumento[]>;
  tipoDocumento: TipoDocumento = {
    nombre: '',
    descripcion: ''
  };

  constructor(private http: HttpClient) {
    this.tipoDocumentos$ = of(); // inicializa como un Observable vacío
    this.obtenerTiposDocumento();
  }

  obtenerTiposDocumento() {
    return this.tipoDocumentos$ = this.http.get<TipoDocumento[]>('/api/v1/tipos-documento');
  }

  obtenerTipoDocumentoPorId(id: number): Observable<TipoDocumento> {
    return this.http.get<TipoDocumento>(`/api/v1/tipos-documento/${id}`);
  }

  crearTipoDocumento() {
    this.http.post('/api/v1/tipos-documento', this.tipoDocumento).subscribe(() => {
      console.log('Tipo de documento creado exitosamente!');
      this.obtenerTiposDocumento();
    });
  }

  actualizarTipoDocumento(id: number) {
    this.http.put(`/api/v1/tipos-documento/${id}`, this.tipoDocumento).subscribe(() => {
      console.log('Tipo de documento actualizado exitosamente!');
      this.obtenerTiposDocumento();
    });
  }

  eliminarTipoDocumento(id: number) {
    this.http.delete(`/api/v1/tipos-documento/${id}`).subscribe(() => {
      console.log('Tipo de documento eliminado exitosamente!');
      this.obtenerTiposDocumento();
    });
  }
}