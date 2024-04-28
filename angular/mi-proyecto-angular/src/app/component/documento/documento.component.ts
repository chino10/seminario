import { Component } from '@angular/core';
import { Documento } from '../../modelos/Documento';
import { DocumentoService } from '../../service/documento.service';

@Component({
  selector: 'app-documento',
  templateUrl: './documento.component.html',
  styleUrls: ['./documento.component.css']
})
export class DocumentoComponent {
  listaDocumentos: Documento[] = []; // Aquí estás declarando una propiedad con el tipo Documento[]

  constructor(private documentoService: DocumentoService) { }

  ngOnInit(): void {
    this.documentoService.obtenerDocumentos().subscribe(documentos => {
      this.listaDocumentos = documentos;
    });
  }
}