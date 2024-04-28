import { Consulado } from "./Consulado";
import { TipoTramite } from "./TipoTramite";

export interface Tramite {
  id?: number;
  consulado: Consulado | null;
  tipoTramite: TipoTramite | null;
  importe: number | null;
}