import { TipoDocumento } from "./TipoDocumento";

export interface Documento {
  id?: number;
  nombre: string;
  descripcion: string;
  tipoDocumento: TipoDocumento | null;
  fechaPresentacion: string;
}