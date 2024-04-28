import { Cliente } from "./Cliente";
import { Consulado } from "./Consulado";
import { Documento } from "./Documento";
import { Estado } from "./Estado";
import { Observacion } from "./Observacion";
import { Tramite } from "./Tramite";
import { Usuario } from "./Usuario";

export interface DetalleTramite {
  id?: number;
  listaClientes: Cliente[] | null;
  listaDocumentos: Documento[] | null;
  estado: Estado | null;
  listaUsuarios: Usuario[] | null;
  consulado: Consulado | null;
  fechaInicio: string;
  fechaFin: string;
  tramite: Tramite | null;
  listaObservaciones: Observacion[] | null;
}