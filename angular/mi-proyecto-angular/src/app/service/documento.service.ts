import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Documento } from '../modelos/Documento';

@Injectable({
  providedIn: 'root'
})
export class DocumentoService {
  documentos$: Observable<Documento[]>;
  documento: Documento = {
    nombre: '',
    descripcion: '',
    tipoDocumento: null,
    fechaPresentacion: ''
  };

  constructor(private http: HttpClient) {
    this.documentos$ = of(); // inicializa como un Observable vacío
    this.obtenerDocumentos();
  }

  obtenerDocumentos() {
    return this.documentos$ = this.http.get<Documento[]>('/api/v1/documentos');
  }

  obtenerDocumentoPorId(id: number): Observable<Documento> {
    return this.http.get<Documento>(`/api/v1/documentos/${id}`);
  }

  crearDocumento() {
    this.http.post('/api/v1/documentos', this.documento).subscribe(() => {
      console.log('Documento creado exitosamente!');
      this.obtenerDocumentos();
    });
  }

  actualizarDocumento(id: number) {
    this.http.put(`/api/v1/documentos/${id}`, this.documento).subscribe(() => {
      console.log('Documento actualizado exitosamente!');
      this.obtenerDocumentos();
    });
  }

  eliminarDocumento(id: number) {
    this.http.delete(`/api/v1/documentos/${id}`).subscribe(() => {
      console.log('Documento eliminado exitosamente!');
      this.obtenerDocumentos();
    });
  }
}