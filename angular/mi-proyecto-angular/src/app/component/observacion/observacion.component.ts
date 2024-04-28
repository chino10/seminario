import { Component } from '@angular/core';
import { Observacion } from '../../modelos/Observacion';
import { ObservacionService } from '../../service/observacion.service';

@Component({
  selector: 'app-observacion',
  templateUrl: './observacion.component.html',
  styleUrls: ['./observacion.component.css']
})
export class ObservacionComponent {
  listaObservaciones: Observacion[] = []; // Aquí estás declarando una propiedad con el tipo Observacion[]

  constructor(private observacionService: ObservacionService) { }

  ngOnInit(): void {
    this.observacionService.obtenerObservaciones().subscribe(observaciones => {
      this.listaObservaciones = observaciones;
    });
  }
}