import { Component } from '@angular/core';
import { Consulado } from '../../modelos/Consulado';
import { ConsuladoService } from '../../service/consulado.service';

@Component({
  selector: 'app-consulado',
  templateUrl: './consulado.component.html',
  styleUrls: ['./consulado.component.css']
})
export class ConsuladoComponent {
  listaConsulados: Consulado[] = []; // Aquí estás declarando una propiedad con el tipo Consulado[]

  constructor(private consuladoService: ConsuladoService) { }

  ngOnInit(): void {
    this.consuladoService.obtenerConsulados().subscribe(consulados => {
      this.listaConsulados = consulados;
    });
  }
}