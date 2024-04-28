import { Component } from '@angular/core';
import { Pais } from '../../modelos/Pais';
import { PaisService } from '../../service/pais.service';

@Component({
  selector: 'app-pais',
  templateUrl: './pais.component.html',
  styleUrls: ['./pais.component.css']
})
export class PaisComponent {
  listaPaises: Pais[] = []; // Aquí estás declarando una propiedad con el tipo Pais[]

  constructor(private paisService: PaisService) { }

  ngOnInit(): void {
    this.paisService.obtenerPaises().subscribe(paises => {
      this.listaPaises = paises;
    });
  }
}