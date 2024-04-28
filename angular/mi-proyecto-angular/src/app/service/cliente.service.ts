import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Cliente } from '../modelos/Cliente';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class ClienteService {
  clientes$: Observable<Cliente[]>;
  cliente: Cliente = {
    dni: null,
    nombre: '',
    apellido: '',
    email: ''
  };

  constructor(private http: HttpClient) {
    this.clientes$ = of(); // inicializa como un Observable vacío
    this.obtenerClientes();
  }

  obtenerClientes() {
    return this.clientes$ = this.http.get<Cliente[]>('/api/v1/clientes');
  }

  obtenerClientePorId(id: number): Observable<Cliente> {
    return this.http.get<Cliente>(`/api/v1/clientes/${id}`);
  }

  crearCliente() {
    this.http.post('/api/v1/clientes', this.cliente).subscribe(() => {
      console.log('Cliente creado exitosamente!');
      this.obtenerClientes();
    });
  }

  actualizarCliente(id: number) {
    this.http.put(`/api/v1/clientes/${id}`, this.cliente).subscribe(() => {
      console.log('Cliente actualizado exitosamente!');
      this.obtenerClientes();
    });
  }

  eliminarCliente(id: number) {
    this.http.delete(`/api/v1/clientes/${id}`).subscribe(() => {
      console.log('Cliente eliminado exitosamente!');
      this.obtenerClientes();
    });
  }
}
