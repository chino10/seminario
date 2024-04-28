import { Component } from '@angular/core';
import { Tramite } from '../../modelos/Tramite';
import { TramiteService } from '../../service/tramite.service';

@Component({
  selector: 'app-tramite',
  templateUrl: './tramite.component.html',
  styleUrls: ['./tramite.component.css']
})
export class TramiteComponent {
  listaTramites: Tramite[] = []; // Aquí estás declarando una propiedad con el tipo Tramite[]

  constructor(private tramiteService: TramiteService) { }

  ngOnInit(): void {
    this.tramiteService.obtenerTramites().subscribe(tramites => {
      this.listaTramites = tramites;
    });
  }
}