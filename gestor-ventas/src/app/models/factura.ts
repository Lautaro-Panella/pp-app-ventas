import { Cliente } from "./cliente";

export interface Factura{

    facturaId: number | any;
    fecha: Date;
    porcentajeDescuento: number;
    subtotal: number;
    observaciones: string;
    cliente: Cliente;
}