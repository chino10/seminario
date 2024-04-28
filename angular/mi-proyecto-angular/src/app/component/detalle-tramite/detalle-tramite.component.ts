import { Component, OnInit } from '@angular/core';
import { DetalleTramite } from '../../modelos/DetalleTramite';
import { DetalleTramiteService } from '../../service/detalle-tramite.service';

@Component({
  selector: 'app-detalle-tramite',
  templateUrl: './detalle-tramite.component.html',
  styleUrls: ['./detalle-tramite.component.css']
})
export class DetalleTramiteComponent implements OnInit {
  listaDetallesTramite: DetalleTramite[] = []; // Aquí estás declarando una propiedad con el detalle DetalleTramite[]
  detalleTramite: DetalleTramite | undefined;

  constructor(private detalleTramiteService: DetalleTramiteService) { }

  ngOnInit(): void {
    this.detalleTramiteService.obtenerDetallesTramite().subscribe(detallesTramite => {
      // this.listaDetallesTramite = detallesTramite;
      this.detalleTramite = detallesTramite[0];
    });
  }

  onSubmit() {
    this.detalleTramiteService.crearDetalleTramite();
    console.log('Formulario enviado!');
  }
}