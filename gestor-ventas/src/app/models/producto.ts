import { Categoria } from "./categoria";

export interface Producto {

    productoId: number;
    descripcion: string;
    talle: number;
    precioUnitario: number;
    existencias: number;
    categoria: Categoria;
    fechaBaja: Date;
}