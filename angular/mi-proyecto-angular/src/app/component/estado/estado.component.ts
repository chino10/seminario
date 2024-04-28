import { Component } from '@angular/core';
import { Estado } from '../../modelos/Estado';
import { EstadoService } from '../../service/estado.service';

@Component({
  selector: 'app-estado',
  templateUrl: './estado.component.html',
  styleUrls: ['./estado.component.css']
})
export class EstadoComponent {
  listaEstados: Estado[] = []; // Aquí estás declarando una propiedad con el tipo Estado[]

  constructor(private estadoService: EstadoService) { }

  ngOnInit(): void {
    this.estadoService.obtenerEstados().subscribe(estados => {
      this.listaEstados = estados;
    });
  }
}