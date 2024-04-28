import { Component } from '@angular/core';
import { Rol } from '../../modelos/Rol';
import { RolService } from '../../service/rol.service';

@Component({
  selector: 'app-rol',
  templateUrl: './rol.component.html',
  styleUrls: ['./rol.component.css']
})
export class RolComponent {
  listaRoles: Rol[] = []; // Aquí estás declarando una propiedad con el tipo Rol[]

  constructor(private rolService: RolService) { }

  ngOnInit(): void {
    this.rolService.obtenerRoles().subscribe(roles => {
      this.listaRoles = roles;
    });
  }
}