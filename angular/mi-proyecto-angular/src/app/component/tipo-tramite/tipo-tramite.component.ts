import { Component } from '@angular/core';
import { TipoTramite } from '../../modelos/TipoTramite';
import { TipoTramiteService } from '../../service/tipo-tramite.service';

@Component({
  selector: 'app-tipo-tramite',
  templateUrl: './tipo-tramite.component.html',
  styleUrls: ['./tipo-tramite.component.css']
})
export class TipoTramiteComponent {
  listaTiposTramite: TipoTramite[] = []; // Aquí estás declarando una propiedad con el tipo TipoTramite[]

  constructor(private tipoTramiteService: TipoTramiteService) { }

  ngOnInit(): void {
    this.tipoTramiteService.obtenerTiposTramite().subscribe(tiposTramite => {
      this.listaTiposTramite = tiposTramite;
    });
  }
}