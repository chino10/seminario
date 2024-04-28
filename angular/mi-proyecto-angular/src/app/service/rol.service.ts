import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { Rol } from '../modelos/Rol';

@Injectable({
  providedIn: 'root'
})
export class RolService {
  roles$: Observable<Rol[]>;
  rol: Rol = {
    nombre: '',
    descripcion: ''
  };

  constructor(private http: HttpClient) {
    this.roles$ = of(); // inicializa como un Observable vacío
    this.obtenerRoles();
  }

  obtenerRoles() {
    return this.roles$ = this.http.get<Rol[]>('/api/v1/roles');
  }

  obtenerRolPorId(id: number): Observable<Rol> {
    return this.http.get<Rol>(`/api/v1/roles/${id}`);
  }

  crearRol() {
    this.http.post('/api/v1/roles', this.rol).subscribe(() => {
      console.log('Rol creado exitosamente!');
      this.obtenerRoles();
    });
  }

  actualizarRol(id: number) {
    this.http.put(`/api/v1/roles/${id}`, this.rol).subscribe(() => {
      console.log('Rol actualizado exitosamente!');
      this.obtenerRoles();
    });
  }

  eliminarRol(id: number) {
    this.http.delete(`/api/v1/roles/${id}`).subscribe(() => {
      console.log('Rol eliminado exitosamente!');
      this.obtenerRoles();
    });
  }
}