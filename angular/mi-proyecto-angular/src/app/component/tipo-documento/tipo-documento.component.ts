import { Component } from '@angular/core';
import { TipoDocumento } from '../../modelos/TipoDocumento';
import { TipoDocumentoService } from '../../service/tipo-documento.service';

@Component({
  selector: 'app-tipo-documento',
  templateUrl: './tipo-documento.component.html',
  styleUrls: ['./tipo-documento.component.css']
})
export class TipoDocumentoComponent {
  listaTiposDocumento: TipoDocumento[] = []; // Aquí estás declarando una propiedad con el tipo TipoDocumento[]

  constructor(private tipoDocumentoService: TipoDocumentoService) { }

  ngOnInit(): void {
    this.tipoDocumentoService.obtenerTiposDocumento().subscribe(tiposDocumento => {
      this.listaTiposDocumento = tiposDocumento;
    });
  }
}