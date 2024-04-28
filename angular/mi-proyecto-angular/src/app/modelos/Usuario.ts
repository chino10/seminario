import { Rol } from "./Rol";

export interface Usuario {
  id?: number;
  dni: number | null;
  nombre: string;
  apellido: string;
  email: string;
  rol: Rol | null;
}