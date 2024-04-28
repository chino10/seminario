import { Component } from '@angular/core';
import { Usuario } from '../../modelos/Usuario';
import { UsuarioService } from '../../service/usuario.service';

@Component({
  selector: 'app-usuario',
  templateUrl: './usuario.component.html',
  styleUrls: ['./usuario.component.css']
})
export class UsuarioComponent {
  listaUsuarios: Usuario[] = []; // Aquí estás declarando una propiedad con el tipo Usuario[]

  constructor(private usuarioService: UsuarioService) { }

  ngOnInit(): void {
    this.usuarioService.obtenerUsuarios().subscribe(usuarios => {
      this.listaUsuarios = usuarios;
    });
  }
}