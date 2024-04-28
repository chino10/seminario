import { Component } from '@angular/core';
import { Cliente } from '../../modelos/Cliente';
import { ClienteService } from '../../service/cliente.service';

@Component({
  selector: 'app-cliente',
  templateUrl: './cliente.component.html',
  styleUrls: ['./cliente.component.css']
})
export class ClienteComponent {
  listaClientes: Cliente[] = []; // Aquí estás declarando una propiedad con el tipo Cliente[]

  constructor(private clienteService: ClienteService) { }

  ngOnInit(): void {
    this.clienteService.obtenerClientes().subscribe(clientes => {
      this.listaClientes = clientes;
    });
  }
}