import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Usuario } from '../modelos/Usuario';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {
  usuarios$: Observable<Usuario[]>;
  usuario: Usuario = {
    dni: null,
    nombre: '',
    apellido: '',
    email: '',
    rol: null
  };

  constructor(private http: HttpClient) {
    this.usuarios$ = of(); // inicializa como un Observable vacío
    this.obtenerUsuarios();
  }

  obtenerUsuarios() {
    return this.usuarios$ = this.http.get<Usuario[]>('/api/v1/usuarios');
  }

  obtenerUsuarioPorId(id: number): Observable<Usuario> {
    return this.http.get<Usuario>(`/api/v1/usuarios/${id}`);
  }

  crearUsuario() {
    this.http.post('/api/v1/usuarios', this.usuario).subscribe(() => {
      console.log('Usuario creado exitosamente!');
      this.obtenerUsuarios();
    });
  }

  actualizarUsuario(id: number) {
    this.http.put(`/api/v1/usuarios/${id}`, this.usuario).subscribe(() => {
      console.log('Usuario actualizado exitosamente!');
      this.obtenerUsuarios();
    });
  }

  eliminarUsuario(id: number) {
    this.http.delete(`/api/v1/usuarios/${id}`).subscribe(() => {
      console.log('Usuario eliminado exitosamente!');
      this.obtenerUsuarios();
    });
  }
}
