import { Pais } from "./Pais";

export interface Consulado {
  id?: number;
  pais: Pais | null;
  domicilio: string;
  ciudad: string;
  provincia: string;
}